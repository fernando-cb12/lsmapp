package com.example.lsmapp.ui.screens.lesson

import androidx.lifecycle.ViewModel
import com.example.lsmapp.data.model.Lesson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LessonVideoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LessonVideoUiState())
    val uiState: StateFlow<LessonVideoUiState> = _uiState

    fun loadVideo(lesson: Lesson) {
        _uiState.value = LessonVideoUiState(lesson = lesson)
    }
}

data class LessonVideoUiState(
    val lesson: Lesson? = null
)
