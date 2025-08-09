package com.example.fakestoreappproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fakestoreappproject.ui.navigation.Screen
import com.example.fakestoreappproject.ui.screens.CartScreen
import com.example.fakestoreappproject.ui.screens.CategoryProductScreen
import com.example.fakestoreappproject.ui.screens.CategoryScreen
import com.example.fakestoreappproject.ui.screens.ProductDetailScreen
import com.example.fakestoreappproject.ui.screens.ProductListScreen
import com.example.fakestoreappproject.ui.themes.FakeStoreAppProjectTheme // Note: "theme" not "themes"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavHost()
        }
    }
}
@Composable
fun AppContent() {
    FakeStoreAppProjectTheme {

           Text("App is working!")
    }
}
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ProductList.route
    ) {
        composable(Screen.Category.route) {
            CategoryScreen(navController)
        }

        composable(
            route = Screen.CategoryProduct.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            CategoryProductScreen(navController, categoryId)
        }

        composable(Screen.ProductList.route) {
            ProductListScreen(navController)
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController, productId)
        }

        composable(Screen.Cart.route) {
            CartScreen(navController)
        }
    }
}






