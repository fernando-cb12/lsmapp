package com.example.lsmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lsmapp.composables.MainScaffold
import com.example.lsmapp.navigation.Route
import com.example.lsmapp.register.RegisterScreen
import com.example.lsmapp.screens.LoginScreen
import com.example.lsmapp.ui.theme.LsmappTheme
import com.example.lsmapp.viewModel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LsmappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppRoot(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppRoot(modifier: Modifier = Modifier) {

    val vm: AppViewModel = viewModel()
    val nav = rememberNavController()

    // Se inicia directamente en el flujo de autenticación
    NavHost(navController = nav, startDestination = Route.Auth.route, modifier = modifier) {

        // AUTH FLOW (sin Drawer/BottomBar)
        composable(Route.Auth.route) {
            AuthNavHost(
                onLoggedIn = {
                    vm.login()
                    nav.navigate(Route.Main.route) {
                        // Al iniciar sesión, se limpia el historial de autenticación
                        popUpTo(Route.Auth.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }

        // MAIN FLOW (con Scaffold + Drawer + BottomBar)
        composable(Route.Main.route) {
            MainScaffold(
                onLogoutClick = { vm.logout() },
                onNavigateToAuth = {
                    // Al cerrar sesión, se navega a la pantalla de autenticación y se limpia el historial
                    nav.navigate(Route.Auth.route) { popUpTo(0) }
                }
            )
        }
    }
}

@Composable
fun AuthNavHost(onLoggedIn: () -> Unit) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Route.Login.route) {
        composable(Route.Login.route) {
            LoginScreen(
                onLogin = { onLoggedIn() },
                onGoToRegister = { nav.navigate(Route.Register.route) }
            )
        }
        composable(Route.Register.route) {
            RegisterScreen(
                onLoginClick = { nav.popBackStack() },
                onRegisterClick = { onLoggedIn() }
            )
        }
    }
}