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
fun QuizScreen(navController: NavController, viewModel: QuizViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Pregunta 1")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = uiState.question)
        Spacer(modifier = Modifier.height(16.dp))
        uiState.options.forEach { option ->
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = option)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(AppScreens.CongratulationsScreen.route) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Terminar Lección")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    // Can't preview with NavController
}
