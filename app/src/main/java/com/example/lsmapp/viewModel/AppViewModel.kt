package com.example.lsmapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lsmapp.dataStore.AppDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


data class AuthState(
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean = false)


class AppViewModel(app: Application) : AndroidViewModel(app) {
    private val dataStore = AppDataStore(app)

    private val _auth = MutableStateFlow(AuthState())
    val auth: StateFlow<AuthState> = _auth.asStateFlow()

    init { loadProfile() }


    private fun loadProfile(){
        viewModelScope.launch {
            val loggedIn = dataStore.isLoggedIn()
                _auth.value = AuthState(isLoading = false, isLoggedIn = loggedIn)
        }
    }


    fun login()  { viewModelScope.launch { dataStore.setLoggedIn(true) } }
    fun logout() { viewModelScope.launch { dataStore.setLoggedIn(false) } }
}