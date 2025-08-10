package com.example.fakestoreappproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fakestoreappproject.ui.navigation.FakeStoreAppNavGraph
import com.example.fakestoreappproject.ui.themes.FakeStoreAppProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreAppProjectTheme {
                FakeStoreAppNavGraph()
            }
        }
    }
}


