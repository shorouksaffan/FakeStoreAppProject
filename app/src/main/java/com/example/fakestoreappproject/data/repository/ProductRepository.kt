package com.example.fakestoreappproject.data.repository

import com.example.fakestoreappproject.data.model.CartItem
import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult

interface ProductRepository {
    suspend fun getProducts(offset: Int, limit: Int): ApiResult<List<Product>>

    suspend fun getProductById(id: Int): ApiResult<Product>

    suspend fun getProductsByCategory(categoryId: Int): ApiResult<List<Product>>

    suspend fun getCategories(): ApiResult<List<Category>>

    suspend fun addCartItem(cartItem: CartItem)

    suspend fun getCartItems(): List<CartItem>

    suspend fun deleteCartItem(productId: Int)

    suspend fun clearCart()

    suspend fun getCartItemByProductId(productId: Int): CartItem

    suspend fun isProductInCart(productId: Int): Int

    suspend fun updateCartItemQuantity(productId: Int, quantity: Int)

    suspend fun getTotalItemsInCart(): Int

    suspend fun getTotalPriceInCart(): Double
}

