package com.example.lsmapp.data.model

import androidx.annotation.DrawableRes

data class Lesson(
    val id: String,
    val title: String,
    val topicId: String,
    val videoUrl: String,
    val content: String
)