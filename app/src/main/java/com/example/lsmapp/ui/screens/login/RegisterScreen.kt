package com.example.lsmapp.ui.screens.login


import CustomInputField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lsmapp.R
import kotlinx.coroutines.flow.collectLatest
import com.example.lsmapp.ui.screens.login.components.*

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigateToHome.collectLatest {
            onNavigateToHome()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryDarkGrey
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            // ---------- Logo ----------
            // Mantén este tamaño FIJO
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .graphicsLayer(clip = false),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop,
                    // Force the image to occupy a huge space, which will then be cropped
                    modifier = Modifier.size(10000.dp)
                )
            }




            Spacer(modifier = Modifier.height(100.dp))

            // Email
            CustomInputField(
                value = email,
                onValueChange = viewModel::updateEmail,
                placeholder = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Password
            CustomInputField(
                value = password,
                onValueChange = viewModel::updatePassword,
                placeholder = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Confirm Password
            CustomInputField(
                value = confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                placeholder = "Confirm Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Link para ir al login
            Text(
                text = "Already have an account? Log in",
                color = TextLinkColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clickable { onNavigateToLogin() }
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Botón de registrar
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clickable { viewModel.register() }
                    .background(LoginButtonColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Register",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "@forodeinclusiontec",
                color = TextLinkColor.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        viewModel = RegisterViewModel(),
        onNavigateToHome = {},
        onNavigateToLogin = {}
    )
}
