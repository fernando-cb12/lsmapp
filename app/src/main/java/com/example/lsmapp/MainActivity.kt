package com.example.lsmapp

import LoginViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lsmapp.data.repository.AuthRepository
import com.example.lsmapp.ui.screens.login.LoginScreen
import com.example.lsmapp.ui.screens.login.LoginViewModelFactory
import com.example.lsmapp.ui.theme.LsmappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LsmappTheme {

                // Creamos el repositorio real
                val authRepository = AuthRepository()

                // Creamos el ViewModel con factory
                val loginViewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                    factory = LoginViewModelFactory(authRepository)
                )


                LoginScreen(
                    viewModel = loginViewModel,
                    onNavigateToHome = {
                        // Navegación REAL
                        println("Navigating to Home")
                    },
                    onNavigateToRegistration = {
                        // Navegación REAL
                        println("Navigating to Registration")
                    }
                )
            }
        }
    }
}