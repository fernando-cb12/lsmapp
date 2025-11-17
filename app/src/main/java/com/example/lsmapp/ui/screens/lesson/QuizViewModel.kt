package com.example.lsmapp.ui.screens.lesson

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState

}

data class QuizUiState(
    val question: String = "¿Cómo se dice Hola en lenguaje de señas?",
    val options: List<String> = listOf("Opción A", "Opción B"),
    val correctAnswer: String = "Opción A"
)
