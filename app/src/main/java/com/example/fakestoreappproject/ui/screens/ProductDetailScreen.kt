package com.example.fakestoreappproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProductDetailScreen(navController: NavHostController, x1: String) {
    Column {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back")
        }

    }}
