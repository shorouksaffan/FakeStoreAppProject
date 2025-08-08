package com.example.fakestoreappproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fakestoreappproject.ui.themes.FakeStoreAppProjectTheme // Note: "theme" not "themes"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    FakeStoreAppProjectTheme {

           Text("App is working!")
    }
}