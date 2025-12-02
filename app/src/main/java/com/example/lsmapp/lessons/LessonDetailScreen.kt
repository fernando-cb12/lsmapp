package com.example.lsmapp.lessons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lsmapp.R
import com.example.lsmapp.lessons.Lesson
import com.example.lsmapp.navigation.Route
import com.example.lsmapp.ui.theme.BotonesColor
import com.example.lsmapp.ui.theme.ColorTexto
import com.example.lsmapp.ui.theme.PrimaryBackgroundColor
import com.example.lsmapp.ui.theme.SecondaryBackgroundColor
import com.example.lsmapp.ui.theme.ThirdBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    navController: NavController,
    lessonId: String?,
    viewModel: LessonDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Cargar la lección según el ID
    LaunchedEffect(lessonId) {
        lessonId?.let {
            // Aquí cargarías la lección real desde tu repositorio/base de datos
            // Por ahora usamos datos de ejemplo
            val lesson = when(it) {
                "1" -> Lesson(
                    id = it,
                    title = "Lección 1: Introducción",
                    topicId = "1",
                    videoUrl = "",
                    content = "La Lengua de Señas Mexicana (LSM) es una lengua natural utilizada por la comunidad sorda en México. En esta introducción aprenderás los conceptos básicos:",
                    difficulty = "Difícil",
                    expReward = 15,
                    //imageResId = R.drawable.introduccion,
                    bulletPoints = listOf("Historia de la LSM", "Importancia de la comunicación", "Alfabeto manual")
                )
                "2" -> Lesson(
                    id = it,
                    title = "Lección 2: Saludos básicos",
                    topicId = "1",
                    videoUrl = "",
                    content = "Aprender a saludar es el primer paso para comunicarse en lengua de señas mexicana. Hoy vamos a aprender cómo decir:",
                    difficulty = "Fácil",
                    expReward = 5,
                    //imageResId = R.drawable.saludobasico,
                    bulletPoints = listOf("Hola", "Adiós", "¿Cómo estás?", "Buenos días", "Buenas tardes")
                )
                else -> Lesson(
                    id = it,
                    title = "Lección $it",
                    topicId = "1",
                    videoUrl = "",
                    content = "Contenido de la lección",
                    difficulty = "Media",
                    expReward = 10,
                    bulletPoints = listOf("Punto 1", "Punto 2", "Punto 3")
                )
            }
            viewModel.loadLesson(lesson)
        }
    }

    Scaffold(
        containerColor = PrimaryBackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.lesson?.title ?: "",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = ColorTexto
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBackgroundColor)
            )
        }
    ) { innerPadding ->
        uiState.lesson?.let { lesson ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = ThirdBackgroundColor)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
//                        Image(
//                            painter = painterResource(id = lesson.imageResId),
//                            contentDescription = lesson.title,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.fillMaxSize()
//                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = lesson.difficulty,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(8.dp))
                                    .background(ThirdBackgroundColor)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                            Text(
                                text = "${lesson.expReward} xp",
                                color = ColorTexto,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SecondaryBackgroundColor.copy(alpha = 0.5f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = lesson.content, color = ColorTexto, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(16.dp))
                lesson.bulletPoints.forEach { point ->
                    Text(
                        text = "• $point",
                        color = ColorTexto,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { navController.navigate(Route.Quiz.createRoute(lesson.id)) },
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(containerColor = BotonesColor)
                ) {
                    Text(text = "Empezar", color = SecondaryBackgroundColor, fontSize = 16.sp)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = SecondaryBackgroundColor
                    )
                }
            }
        }
    }
}
