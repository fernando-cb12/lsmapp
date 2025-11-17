package com.example.lsmapp.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    // Evento de navegación al completar registro
    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome = _navigateToHome

    fun updateEmail(value: String) { _email.value = value }
    fun updatePassword(value: String) { _password.value = value }
    fun updateConfirmPassword(value: String) { _confirmPassword.value = value }

    fun register() {
        viewModelScope.launch {
            // Luego aquí agregas Firebase o tu backend real
            if (password.value == confirmPassword.value && password.value.isNotEmpty()) {
                _navigateToHome.emit(Unit)
            }
        }
    }
}
