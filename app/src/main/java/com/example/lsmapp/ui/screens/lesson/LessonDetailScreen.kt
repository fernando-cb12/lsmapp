package com.example.lsmapp.ui.screens.lesson

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lsmapp.R
import com.example.lsmapp.data.model.Lesson
import com.example.lsmapp.ui.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(navController: NavController, lessonId: String?, viewModel: LessonDetailViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(lessonId) {
        lessonId?.let {
            viewModel.loadLesson(Lesson(id = it, title = "Lección $it: Saludos básicos", topicId = "1", videoUrl = "", content = "Aprender a saludar es el primer paso para comunicarse en lengua de señas mexicana. Hoy vamos a aprender cómo decir:"))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(uiState.lesson?.title ?: "") })
        }
    ) { innerPadding ->
        uiState.lesson?.let { lesson ->
            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)) {
                Card {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Medium") // Placeholder
                            Text(text = "10 xp")   // Placeholder
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = lesson.content)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "- Hola")
                Text(text = "- Adiós")
                Text(text = "- ¿Cómo estás?")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate(AppScreens.LessonVideoScreen.route) }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Empezar")
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
    }
}
