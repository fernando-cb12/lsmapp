package com.example.lsmapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.lsmapp.data.model.Lesson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        // Dummy data for preview
        _uiState.value = HomeUiState(
            lessons = listOf(
                Lesson(id = "1", title = "Lección 1: Introduccion", topicId = "1", videoUrl = "", content = ""),
                Lesson(id = "2", title = "Lección 2: Saludos básicos", topicId = "1", videoUrl = "", content = "")
            )
        )
    }
}

data class HomeUiState(
    val lessons: List<Lesson> = emptyList()
)
