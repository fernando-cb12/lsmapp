package com.example.lsmapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lsmapp.ui.screens.home.HomeScreen
import com.example.lsmapp.ui.screens.lesson.CongratulationsScreen
import com.example.lsmapp.ui.screens.lesson.LessonDetailScreen
import com.example.lsmapp.ui.screens.lesson.LessonVideoScreen
import com.example.lsmapp.ui.screens.lesson.QuizScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(AppScreens.LessonDetailScreen.route + "/{lessonId}") { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")
            LessonDetailScreen(navController = navController, lessonId = lessonId)
        }
        composable(AppScreens.LessonVideoScreen.route) {
            LessonVideoScreen(navController = navController)
        }
        composable(AppScreens.QuizScreen.route) {
            QuizScreen(navController = navController)
        }
        composable(AppScreens.CongratulationsScreen.route) {
            CongratulationsScreen(navController = navController)
        }
    }
}

sealed class AppScreens(val route: String) {
    object HomeScreen : AppScreens("home_screen")
    object LessonDetailScreen : AppScreens("lesson_detail_screen")
    object LessonVideoScreen : AppScreens("lesson_video_screen")
    object QuizScreen : AppScreens("quiz_screen")
    object CongratulationsScreen : AppScreens("congratulations_screen")
}
