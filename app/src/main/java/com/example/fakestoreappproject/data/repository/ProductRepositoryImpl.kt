package com.example.fakestoreappproject.data.repository

import android.content.Context
import com.example.fakestoreappproject.data.local.AppDatabase
import com.example.fakestoreappproject.data.local.CartDao
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

    override suspend fun addProductToCart(product: Product) {
        cartDao.addProductToCart(product)
    }

    override suspend fun getCartProducts(): List<Product> {
        return cartDao.getCartProducts()
    }
}
