package com.example.lsmapp.auth

import android.content.Context
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class SupabaseSession(
    val access_token: String,
    val refresh_token: String,
    val expires_in: Int? = null,
    val token_type: String? = null,
    val provider: String? = null,
    val user: JsonObject
)

class GoogleAuthRepository(
    private val supabaseUrl: String,
    private val supabaseAnonKey: String
) {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    /**
     * Intercambia el idToken (proporcionado por GoogleSignIn) por una sesión de Supabase.
     * Lanza Exception si hay error.
     */
    suspend fun exchangeIdTokenForSession(idToken: String): SupabaseSession {
        val url = "$supabaseUrl/auth/v1/token?grant_type=id_token"

        val response = httpClient.post(url) {
            header("apikey", supabaseAnonKey)
            header("Content-Type", "application/json")
            setBody(mapOf(
                "id_token" to idToken,
                "provider" to "google"
            ))
        }

        if (!response.status.isSuccess()) {
            val bodyText = try { response.body<String>() } catch (e: Exception) { "" }
            throw Exception("Supabase token exchange failed: ${response.status}. $bodyText")
        }

        // Parse response as SupabaseSession
        val session: SupabaseSession = response.body()
        return session
    }

    /**
     * Upsert minimal user row into public.users using PostgREST (rest/v1/users).
     * Use Prefer: resolution=merge-duplicates to upsert.
     * This call is best-effort: no exception thrown to avoid breaking login flow.
     */
    suspend fun upsertUserFromSession(session: SupabaseSession) {
        try {
            val userObj = session.user.jsonObject
            val userId = userObj["id"]?.jsonPrimitive?.contentOrNull ?: return
            val email = userObj["email"]?.jsonPrimitive?.contentOrNull
            val metadata = userObj["user_metadata"]?.jsonObject
            val name = metadata?.get("name")?.jsonPrimitive?.contentOrNull

            val payload = mutableMapOf<String, Any?>("user_id" to userId)
            email?.let { payload["email"] = it }
            name?.let { payload["name"] = it }

            httpClient.post("$supabaseUrl/rest/v1/users") {
                header("apikey", supabaseAnonKey)
                header("Authorization", "Bearer ${session.access_token}")
                header("Content-Type", "application/json")
                header("Prefer", "resolution=merge-duplicates")
                setBody(payload.filterValues { it != null })
            }
        } catch (e: Exception) {
            // Log but don't rethrow: user upsert is non-fatal for login
            println("Failed to upsert user: ${e.message}")
        }
    }
}