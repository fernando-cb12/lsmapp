package com.example.lsmapp.ui.screens.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lsmapp.ui.navigation.AppScreens

@Composable
fun LessonVideoScreen(navController: NavController, viewModel: LessonVideoViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    uiState.lesson?.let { lesson ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = lesson.title)
            Spacer(modifier = Modifier.height(16.dp))
            // Video player will be added here later
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "¡Mira el video para aprender como decir Hola!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(AppScreens.QuizScreen.route) }) {
                Text(text = "Seguir")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessonVideoScreenPreview() {
    // Can't preview with NavController
}
