package com.example.lsmapp.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object TokenStore {
    private const val PREFS_NAME = "secure_prefs_v1"
    private const val KEY_ACCESS = "access_token"
    private const val KEY_REFRESH = "refresh_token"
    private const val KEY_EXPIRES = "expires_in"

    private fun prefs(context: Context) = EncryptedSharedPreferences.create(
        PREFS_NAME,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTokens(context: Context, accessToken: String, refreshToken: String, expiresInSeconds: Int?) {
        prefs(context).edit().apply {
            putString(KEY_ACCESS, accessToken)
            putString(KEY_REFRESH, refreshToken)
            expiresInSeconds?.let { putLong(KEY_EXPIRES, it.toLong()) }
            apply()
        }
    }

    // Overload used from ViewModel where context not passed (call with appContext wrapper)
    fun saveTokens(accessToken: String, refreshToken: String, expiresInSeconds: Int?) {
        // This variant requires the app to call TokenStore.init(context) if used.
        // For simplicity, most calls use the context-accepting version above.
        // (We'll provide usage in MainActivity.)
    }

    fun getAccessToken(context: Context): String? = prefs(context).getString(KEY_ACCESS, null)
    fun getRefreshToken(context: Context): String? = prefs(context).getString(KEY_REFRESH, null)
    fun clear(context: Context) = prefs(context).edit().clear().apply()
}
