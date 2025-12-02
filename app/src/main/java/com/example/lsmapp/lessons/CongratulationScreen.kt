package com.example.lsmapp.lessons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lsmapp.navigation.Route
import com.example.lsmapp.ui.theme.ColorTexto
import com.example.lsmapp.ui.theme.PrimaryBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CongratulationsScreen(navController: NavController, lessonId: String?) {
    Scaffold(
        containerColor = PrimaryBackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "¡Felicidades!",
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
                .clickable {
                    navController.popBackStack(Route.Main.route, inclusive = false)
                }
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¡Felicidades por terminar la lección!",
                color = ColorTexto,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
//            Image(
//                painter = painterResource(id = R.drawable.smilignhamster),
//                contentDescription = "Hámster sonriente",
//                modifier = Modifier.size(180.dp)
//            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Experiencia adquirida",
                color = ColorTexto,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "5 exp",
                color = ColorTexto,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Toca para continuar",
                color = ColorTexto.copy(alpha = 0.7f),
                fontSize = 16.sp
            )
        }
    }
}