package com.example.lsmapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lsmapp.auth.GoogleAuthRepository
import com.example.lsmapp.auth.GoogleAuthViewModel
import com.example.lsmapp.login.GoogleSignInButton
import com.example.lsmapp.ui.theme.LsmappTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class MainActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var viewModel: GoogleAuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
// Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("400187479750-l3ifrmg56qlnuggfuten373bv6cg9bpd.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
// Handle Google Sign-In result
        val googleSignInLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                val idToken = account.idToken
                if (idToken != null) {
                    viewModel.signInWithGoogleIdToken(idToken)
                }
            } catch (e: Exception) {
                Log.e("GoogleSignIn", "Sign-in failed", e)
            }
        }
        setContent {
            LsmappTheme {
                val context = this
                val repository = remember {
                    GoogleAuthRepository(
                        context = context,
                        supabaseUrl = "https://dervnjkshlnntwloyzen.supabase.co",
                        supabaseAnonKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRlcnZuamtzaGxubnR3bG95emVuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjMzMzc5MzksImV4cCI6MjA3ODkxMzkzOX0.Ahx5rcaNfnrU_fhhEL83tlZk1zrVAPLlR06-4a6lwqQ"
                    )
                }
                viewModel = remember { GoogleAuthViewModel(repository) }
                WelcomeScreen(
                    onGoogleSignIn = {
                        googleSignInLauncher.launch(googleSignInClient.signInIntent)
                    }
                )
            }
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { uri: Uri ->
            val code = uri.getQueryParameter("code")
            val idToken = uri.getQueryParameter("id_token")
            Log.d("OAuthRedirect", "code: $code, id_token: $idToken, uri: $uri")
        }
    }
}


@Composable
fun WelcomeScreen(onGoogleSignIn: (() -> Unit)? = null) {
    val context = LocalContext.current
    val repository = remember {
        GoogleAuthRepository(
            context = context,
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
