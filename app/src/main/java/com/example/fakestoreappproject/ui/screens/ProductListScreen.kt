package com.example.fakestoreappproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.fakestoreappproject.ui.navigation.Screen


@Composable
fun ProductListScreen(navController: NavHostController) {
    Column {
        Button(onClick = {
            navController.navigate(Screen.CategoryProduct.createRoute(5))
        }) {
            Text("View Electronics")
        }
        Button(onClick = {
            navController.navigate(Screen.ProductDetail.createRoute(123))
        }) {
            Text("View Product 123")
        }
        Button(onClick = {
            navController.navigate(Screen.Cart.route)
        }) {
            Text("Add to Cart")
        }


    }
}