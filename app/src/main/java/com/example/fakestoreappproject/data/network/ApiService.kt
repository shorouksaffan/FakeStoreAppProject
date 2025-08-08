package com.example.fakestoreappproject.data.network

import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int, @Query("offset") offset: Int): List<Product>

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("categories/{id}/products")
    suspend fun getProductsByCategory(@Path("id") id: Int): List<Product>
}
