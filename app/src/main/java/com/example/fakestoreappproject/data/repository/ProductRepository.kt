package com.example.fakestoreappproject.data.repository

import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult

interface ProductRepository {
    suspend fun getProducts(): ApiResult<List<Product>>

    suspend fun getProductById(id: Int): ApiResult<Product>

    suspend fun getProductsByCategory(categoryId: Int): ApiResult<List<Product>>

    suspend fun getCategories(): ApiResult<List<Category>>

    suspend fun addProductToCart(product: Product)

    suspend fun getCartProducts(): List<Product>
}

