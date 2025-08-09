package com.example.fakestoreappproject.data.repository

import android.content.Context
import com.example.fakestoreappproject.data.local.AppDatabase
import com.example.fakestoreappproject.data.local.CartDao
import com.example.fakestoreappproject.data.model.CartItem
import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult
import com.example.fakestoreappproject.data.network.RetrofitClient

class ProductRepositoryImpl(
    context: Context
) : ProductRepository {
    private val cartDao: CartDao

    init {
        val db = AppDatabase.getInstance(context)
        cartDao = db.cartDao()
    }

    override suspend fun getProducts(offset: Int, limit: Int): ApiResult<List<Product>> {
        return try {
            val products = RetrofitClient.apiService.getProducts(limit, offset)
            ApiResult.Success(products)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun getProductById(id: Int): ApiResult<Product> {
        return try {
            val product = RetrofitClient.apiService.getProductById(id)
            ApiResult.Success(product)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun getProductsByCategory(categoryId: Int): ApiResult<List<Product>> {
        return try {
            val products = RetrofitClient.apiService.getProductsByCategory(categoryId)
            ApiResult.Success(products)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun getCategories(): ApiResult<List<Category>> {
        return try {
            val categories = RetrofitClient.apiService.getCategories()
            ApiResult.Success(categories)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun addCartItem(cartItem: CartItem) {
        cartDao.addCartItem(cartItem)
    }

    override suspend fun getCartItems(): List<CartItem> {
        return cartDao.getCartItems()
    }

    override suspend fun deleteCartItem(productId: Int) {
        cartDao.deleteCartItem(productId)
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }

    override suspend fun getCartItemByProductId(productId: Int): CartItem {
        return cartDao.getCartItemByProductId(productId)
    }

    override suspend fun isProductInCart(productId: Int): Int {
        return cartDao.isProductInCart(productId)
    }

    override suspend fun updateCartItemQuantity(productId: Int, quantity: Int) {
        cartDao.updateCartItemQuantity(productId, quantity)
    }

    override suspend fun getTotalItemsInCart(): Int {
        return cartDao.getTotalItemsInCart()
    }

    override suspend fun getTotalPriceInCart(): Double {
        return cartDao.getTotalPriceInCart()
    }

}
