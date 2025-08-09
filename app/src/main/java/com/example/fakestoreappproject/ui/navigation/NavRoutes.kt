package com.example.fakestoreappproject.ui.navigation

sealed class Screen(val route: String) {
    object Category : Screen("category")
    object CategoryProduct : Screen("category_product/{categoryId}") {
        fun createRoute(categoryId: Int) = "category_product/$categoryId"
    }
    object ProductList : Screen("product_list")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
    object Cart : Screen("cart")
}

