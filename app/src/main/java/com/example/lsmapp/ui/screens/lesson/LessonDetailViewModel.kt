package com.example.lsmapp.ui.screens.lesson

import androidx.lifecycle.ViewModel
import com.example.lsmapp.data.model.Lesson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LessonDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LessonDetailUiState())
    val uiState: StateFlow<LessonDetailUiState> = _uiState

    fun loadLesson(lesson: Lesson) {
        _uiState.value = LessonDetailUiState(lesson = lesson)
    }
}

data class LessonDetailUiState(
    val lesson: Lesson? = null
)
