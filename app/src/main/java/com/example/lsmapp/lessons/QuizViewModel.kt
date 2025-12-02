package com.example.lsmapp.lessons

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState

    init {
        // Inicializar con una pregunta de ejemplo
        _uiState.value = QuizUiState(
            question = "¿Cuál es la seña para 'Hola'?",
            options = listOf("Opción A", "Opción B", "Opción C", "Opción D")
        )
    }
}

data class QuizUiState(
    val question: String = "",
    val options: List<String> = emptyList()
)