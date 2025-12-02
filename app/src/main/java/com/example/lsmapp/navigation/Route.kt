package com.example.lsmapp.navigation

sealed class Route(val route: String) {
    data object Splash : Route("splash")
    data object Auth : Route("auth")
    data object Main : Route("main")

    // Auth internas
    data object Login : Route("login")
    data object Register : Route("register")

    // Main internas
    data object Lesson : Route("lesson")
    data object LessonDetail : Route("lessonDetail/{lessonId}") {
        fun createRoute(lessonId: String) = "lessonDetail/$lessonId"
    }
    data object Quiz : Route("quiz/{lessonId}") {
        fun createRoute(lessonId: String) = "quiz/$lessonId"
    }
    data object Congratulations : Route("congratulations/{lessonId}") {
        fun createRoute(lessonId: String) = "congratulations/$lessonId"
    }
    data object Senas : Route("senas")
    data object Profile : Route("profile")
    data object Settings : Route("settings")
    data object Ranking : Route("ranking")
}