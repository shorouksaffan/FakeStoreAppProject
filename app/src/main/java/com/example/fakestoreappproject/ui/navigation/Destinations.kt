package com.example.fakestoreappproject.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination

@Serializable
sealed interface Graph : Destination

@Serializable
sealed interface Destinations : Graph {

    @Serializable
    data object MainGraph : Graph

    @Serializable
    data object ProductListScreen : Destination

    @Serializable
    data class ProductDetailScreen(val productId: Int) : Destination

    @Serializable
    data object CategoriesScreen : Destination

    @Serializable
    data class CategoryProductScreen(val categoryId: Int, val categoryName: String) : Destination

    @Serializable
    data object CartScreen : Destination
}