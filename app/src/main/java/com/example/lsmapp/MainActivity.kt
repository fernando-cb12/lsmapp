package com.example.lsmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lsmapp.composables.MainScaffold
import com.example.lsmapp.navigation.Route
import com.example.lsmapp.register.RegisterScreen
import com.example.lsmapp.lessons.CongratulationsScreen
import com.example.lsmapp.lessons.LessonDetailScreen
import com.example.lsmapp.lessons.QuizScreen
import com.example.lsmapp.screens.RegisterScreen
import com.example.lsmapp.ui.theme.LsmappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LsmappTheme {
                AppRoot()
            }
        }
    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Auth.route
    ) {
        // AUTH FLOW
        composable(Route.Auth.route) {
            AuthNavHost(
                onLoggedIn = {
                    navController.navigate(Route.Main.route) {
                        popUpTo(Route.Auth.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // MAIN FLOW (con Scaffold + BottomBar)
        composable(Route.Main.route) {
            MainScaffold(mainNavController = navController)
        }

        // LESSON DETAIL (fuera del scaffold)
        composable(
            route = Route.LessonDetail.route,
            arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")
            LessonDetailScreen(
                navController = navController,
                lessonId = lessonId
            )
        }

        // QUIZ
        composable(
            route = Route.Quiz.route,
            arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")
            QuizScreen(
                navController = navController,
                lessonId = lessonId
            )
        }

        // CONGRATULATIONS
        composable(
            route = Route.Congratulations.route,
            arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")
            CongratulationsScreen(
                navController = navController,
                lessonId = lessonId
            )
        }
    }
}

@Composable
fun AuthNavHost(onLoggedIn: () -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Login.route
    ) {
        composable(Route.Login.route) {
            RegisterScreen(
                onRegister = { onLoggedIn() },
                onGoToRegister = { navController.navigate(Route.Register.route) }
            )
        }
        composable(Route.Register.route) {
            RegisterScreen(
                onRegistered = { onLoggedIn() },
                onBackToLogin = { navController.popBackStack() }
            )
        }
    }
}
