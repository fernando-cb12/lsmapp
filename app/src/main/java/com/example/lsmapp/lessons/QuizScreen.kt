package com.example.lsmapp.lessons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lsmapp.navigation.Route
import com.example.lsmapp.ui.theme.BotonesColor
import com.example.lsmapp.ui.theme.ColorTexto
import com.example.lsmapp.ui.theme.PrimaryBackgroundColor
import com.example.lsmapp.ui.theme.SecondaryBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    navController: NavController,
    lessonId: String?,
    viewModel: QuizViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = PrimaryBackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Quiz",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBackgroundColor)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Pregunta 1",
                color = ColorTexto,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = uiState.question,
                color = ColorTexto,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            uiState.options.forEach { option ->
                Button(
                    onClick = {
                        lessonId?.let {
                            navController.navigate(Route.Congratulations.createRoute(it))
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = BotonesColor)
                ) {
                    Text(text = option, color = SecondaryBackgroundColor)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}