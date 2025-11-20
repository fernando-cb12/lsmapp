package com.example.lsmapp.auth

import com.example.lsmapp.auth.TokenStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class GoogleLoginState {
    object Idle : GoogleLoginState()
    object Loading : GoogleLoginState()
    data class Success(val session: SupabaseSession) : GoogleLoginState()
    data class Error(val message: String) : GoogleLoginState()
}

class GoogleAuthViewModel(
    private val repository: GoogleAuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<GoogleLoginState>(GoogleLoginState.Idle)
    val loginState: StateFlow<GoogleLoginState> = _loginState

    fun signInWithGoogleIdToken(idToken: String) {
        viewModelScope.launch {
            _loginState.value = GoogleLoginState.Loading
            try {
                val session = repository.exchangeIdTokenForSession(idToken)

                // Save tokens securely
                TokenStore.saveTokens(
                    accessToken = session.access_token,
                    refreshToken = session.refresh_token,
                    expiresInSeconds = session.expires_in
                )


                // Upsert user row (best-effort)
                launch {
                    repository.upsertUserFromSession(session)
                }

                _loginState.value = GoogleLoginState.Success(session)
            } catch (e: Exception) {
                _loginState.value = GoogleLoginState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _loginState.value = GoogleLoginState.Idle
    }
}