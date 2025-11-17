package com.example.lsmapp.ui.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
// Correct the import for the R class to match your project's package name
import com.example.lsmapp.R


// You might also need to correct this import if LogoContainerColor is in a different package


@Composable
fun LoginLogo() {
    // ----------- LOGO ------------
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(LogoContainerColor, CircleShape)
            .padding(8.dp), // This padding creates the inner margin
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            // Add this modifier to make the image fill the Box
            modifier = Modifier.fillMaxSize()
        )
    }

}
