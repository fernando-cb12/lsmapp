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
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class SupabaseSession(
    val access_token: String,
    val refresh_token: String,
    val user: JsonObject
)

class GoogleAuthRepository(
    private val context: Context,
    private val supabaseUrl: String,
    private val supabaseAnonKey: String
) {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun signInWithGoogleIdToken(idToken: String): Flow<GoogleLoginState> = flow {
        try {
            emit(GoogleLoginState.Loading)

            val response = httpClient.post("$supabaseUrl/auth/v1/token?grant_type=id_token") {
                header("apikey", supabaseAnonKey)
                header("Content-Type", "application/json")
                setBody(mapOf(
                    "id_token" to idToken,
                    "provider" to "google"
                ))
            }

            if (response.status.isSuccess()) {
                val session: SupabaseSession = response.body()
                addUserToUsersTable(session)
                emit(GoogleLoginState.Success(session))
            } else {
                emit(GoogleLoginState.Error("Authentication failed"))
            }
        } catch (e: Exception) {
            emit(GoogleLoginState.Error(e.message ?: "Unknown error"))
        }
    }

    private suspend fun addUserToUsersTable(session: SupabaseSession) {
        try {
            val userId = session.user["id"]?.jsonPrimitive?.contentOrNull ?: return
            val email = session.user["email"]?.jsonPrimitive?.contentOrNull
            // Name can be found in the user_metadata from the Google login
            val name = (session.user["user_metadata"] as? JsonObject)?.get("name")?.jsonPrimitive?.contentOrNull

            val userPayload = mutableMapOf<String, Any?>("user_id" to userId)
            email?.let { userPayload["email"] = it }
            name?.let { userPayload["name"] = it }

            httpClient.post("$supabaseUrl/rest/v1/users") {
                header("apikey", supabaseAnonKey)
                header("Authorization", "Bearer ${session.access_token}")
                header("Content-Type", "application/json")
                // Using "resolution=merge-duplicates" will upsert the record,
                // preventing errors if the user already exists.
                header("Prefer", "resolution=merge-duplicates")
                setBody(userPayload.filterValues { it != null })
            }
        } catch (e: Exception) {
            // Not re-throwing the exception so that the login flow does not fail if this call fails
            println("Failed to add user to DB: ${e.message}")
        }
    }
}

sealed class GoogleLoginState {
    object Loading : GoogleLoginState()
    data class Success(val session: SupabaseSession) : GoogleLoginState()
    data class Error(val message: String) : GoogleLoginState()
}
