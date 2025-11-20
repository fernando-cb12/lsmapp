package com.example.lsmapp.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GoogleAuthViewModelFactory(
    private val repository: GoogleAuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoogleAuthViewModel::class.java)) {
            return GoogleAuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
