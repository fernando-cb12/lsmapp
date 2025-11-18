package com.example.lsmapp.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class GoogleAuthViewModel(
    private val repository: GoogleAuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<GoogleLoginState>(GoogleLoginState.Loading)
    val loginState: StateFlow<GoogleLoginState> = _loginState

    fun signInWithGoogleIdToken(idToken: String) {
        viewModelScope.launch {
            repository.signInWithGoogleIdToken(idToken).collect { state ->
                _loginState.value = state
            }
        }
    }
}