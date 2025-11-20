package com.example.lsmapp.dataStore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "app_prefs"
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

object PrefsKeys { val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in") }

class AppDataStore(private val context: Context) {


    suspend fun isLoggedIn(): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[PrefsKeys.IS_LOGGED_IN] ?: false
    }


    val isLoggedInFlow: Flow<Boolean> =
        context.dataStore.data.map { prefs: Preferences ->
            prefs[PrefsKeys.IS_LOGGED_IN] ?: false
        }

    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[PrefsKeys.IS_LOGGED_IN] = value
        }
    }
}