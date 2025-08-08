package com.example.fakestoreappproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fakestoreappproject.data.model.Product

@Dao
interface CartDao {
    @Insert
    suspend fun addProductToCart(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getCartProducts(): List<Product>
}

