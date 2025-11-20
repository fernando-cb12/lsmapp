package com.example.lsmapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lsmapp.auth.GoogleAuthRepository
import com.example.lsmapp.auth.GoogleAuthViewModel
import com.example.lsmapp.auth.GoogleAuthViewModelFactory
import com.example.lsmapp.login.GoogleSignInButton
import com.example.lsmapp.ui.theme.LsmappTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class MainActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var repo: GoogleAuthRepository

    // ViewModel usando factory para pasar repo
    private val viewModel: GoogleAuthViewModel by viewModels {
        GoogleAuthViewModelFactory(repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // --- Configuración: carga tu Supabase URL y anon key desde BuildConfig o resources ---
        val supabaseUrl = getString(R.string.supabase_url) // e.g. "https://<ref>.supabase.co"
        val supabaseAnonKey = getString(R.string.supabase_anon_key)

        repo = GoogleAuthRepository(
            supabaseUrl = supabaseUrl,
            supabaseAnonKey = supabaseAnonKey
        )

        // GoogleSignInOptions: pide ID token
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_server_client_id)) // tu client id
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Launcher para recibir el Intent de GoogleSignIn
        val googleSignInLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken
                if (idToken != null) {
                    // intercambia idToken por session en Supabase
                    viewModel.signInWithGoogleIdToken(idToken)
                } else {
                    Log.e("MainActivity", "ID token is null")
                }
            } catch (e: Exception) {
                Log.e("GoogleSignIn", "Sign-in failed", e)
            }
        }

        setContent {
            LsmappTheme {
                // Observa el estado del login si quieres mostrar UI
                val state by viewModel.loginState.collectAsState()

                // Aquí renderizas tu WelcomeScreen (pasa la lambda que lanza el intent)
                WelcomeScreen(onGoogleSignIn = {
                    googleSignInLauncher.launch(googleSignInClient.signInIntent)
                })
            }
        }
    }
}


@Composable
fun WelcomeScreen(onGoogleSignIn: (() -> Unit)? = null) {
    val repository = remember {
        GoogleAuthRepository(
            supabaseUrl = "https://dervnjkshlnntwloyzen.supabase.co",
            supabaseAnonKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRlcnZuamtzaGxubnR3bG95emVuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjMzMzc5MzksImV4cCI6MjA3ODkxMzkzOX0.Ahx5rcaNfnrU_fhhEL83tlZk1zrVAPLlR06-4a6lwqQ"
        )
    }
    val viewModel = remember { GoogleAuthViewModel(repository) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome")

        GoogleSignInButton(onClick = {
            onGoogleSignIn?.invoke()
        })
        Spacer(modifier = Modifier.height(12.dp))
        // GoogleSignInSection(viewModel = viewModel)
    }
}
