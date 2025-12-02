package com.example.lsmapp.lessons

import androidx.annotation.DrawableRes

data class Lesson(
    val id: String,
    val title: String,
    val topicId: String,
    val videoUrl: String, // Keep for later use
    val content: String,
    val description: String = "",
    val difficulty: String = "Fácil",
    val expReward: Int = 0,
    @DrawableRes val imageResId: Int = 0, // 0 is an invalid ID, should be replaced
    val bulletPoints: List<String> = emptyList()
)