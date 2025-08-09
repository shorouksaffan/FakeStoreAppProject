package com.example.fakestoreappproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fakestoreappproject.ui.navigation.Screen

@Composable
fun CategoryProductScreen(navController: NavHostController, x1: String) {
    Column {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back")
        }

    }
}
