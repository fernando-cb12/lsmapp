package com.example.lsmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lsmapp.ui.navigation.AppNavigation
import com.example.lsmapp.ui.theme.LsmappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LsmappTheme {
                AppNavigation()
            }
        }
    }
}