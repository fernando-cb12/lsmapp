package com.example.lsmapp.ui.screens.lesson

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lsmapp.R

@Composable
fun CongratulationsScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "¡Felicidades por terminar la lección!")
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Experiencia adquirida")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "5 exp")
    }
}

@Preview(showBackground = true)
@Composable
fun CongratulationsScreenPreview() {
    // Can't preview with NavController
}
