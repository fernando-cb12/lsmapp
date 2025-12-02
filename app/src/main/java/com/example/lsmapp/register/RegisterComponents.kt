package com.example.lsmapp.register

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lsmapp.R
import com.example.lsmapp.auth.AuthManager
import com.example.lsmapp.auth.AuthResponse
import com.example.lsmapp.ui.theme.LsmappTheme
import com.example.lsmapp.ui.theme.black
import com.example.lsmapp.ui.theme.darkGray
import com.example.lsmapp.ui.theme.darkPurple
import com.example.lsmapp.ui.theme.purple
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@Composable
fun RegisterScreen(onRegistered: () -> Unit, onBackToLogin: () -> Unit) {

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("")}
    val context = LocalContext.current
    val authManager = remember {
        AuthManager(context)
    }
    val coroutineScope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        contentAlignment = Alignment.TopCenter
    ) {
        Gradient()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RegisterHeader()

            Spacer(modifier = Modifier.height(44.dp))

            GoogleSignInButton(
                onClick = {
                    authManager.LoginGoogleUser()
                        .onEach { result ->
                            if (result is AuthResponse.Success) {
                                onRegistered()
                            }else{
                                Log.d("auth", "Google failure")
                            }
                        }
                        .launchIn(coroutineScope)
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 30.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.2f))
                )

                Text(
                    text= "Or",
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.2f))
                )
            }

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Email",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                TextField(
                    value = emailValue,
                    onValueChange = {newValue -> emailValue = newValue},
                    placeholder = {
                        Text(
                            text = "inclusion@example.com",
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = darkGray,
                        unfocusedContainerColor = darkGray
                    ),
                    modifier= Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Password",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                TextField(
                    value = passwordValue,
                    onValueChange = {newValue -> passwordValue = newValue},
                    placeholder = {
                        Text(
                            text = "Enter your password",
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = darkGray,
                        unfocusedContainerColor = darkGray
                    ),
                    modifier= Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    authManager.signUpWithEmail(emailValue, passwordValue)
                        .onEach { result ->
                            if (result is AuthResponse.Success) {
                                onRegistered()
                            }else{
                                Log.d("auth", "Email failure")
                            }
                        }
                        .launchIn(coroutineScope)

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sign up",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))

            TextButton(
                onClick = onBackToLogin
            ){
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Light,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        ){
                            append("Already have an account? ")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        ) {
                            append("Log in")
                        }
                    }
                )
            }

        }
    }

}

@Composable
private fun GoogleSignInButton(onClick: () -> Unit = {}) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_google),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Sign in With Google",
            color = Color.White,
            modifier = Modifier.padding(vertical = 4.dp)
        )

    }
}
@Composable
fun RegisterHeader() {
    Text(
        text = "Crear una cuenta",
        style = MaterialTheme.typography.titleLarge,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.padding(8.dp))

    Text(
        text = "Ingresa tus datos para crear una cuenta",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White
    )
}

@Preview
@Composable
private fun RegisterPreview() {
    LsmappTheme {
        RegisterScreen(onRegistered = {}, onBackToLogin = {})
    }
}


@Composable
fun Gradient(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.35f)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        purple,
                        darkPurple,
                        black
                    )
                )
            )
    )
}
