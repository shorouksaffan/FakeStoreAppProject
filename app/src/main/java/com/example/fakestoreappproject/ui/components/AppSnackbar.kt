package com.example.fakestoreappproject.ui.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppSnackbar(snackbarData: SnackbarData) {
    Snackbar(
        snackbarData = snackbarData,
        containerColor = Color(0xFF323232),
        contentColor = Color.White
    )
}