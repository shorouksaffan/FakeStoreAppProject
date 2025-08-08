package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CartState {
    data class Success(val products: List<Product>) : CartState()
    data class Error(val message: String) : CartState()
    object Loading : CartState()
}

class CartViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _cartState = MutableStateFlow<CartState>(CartState.Loading)
    val cartState: StateFlow<CartState> = _cartState

    fun getCartProducts() {
        viewModelScope.launch {
            _cartState.value = CartState.Loading
            try {
                val products = productRepository.getCartProducts()
                _cartState.value = CartState.Success(products)
            } catch (e: Exception) {
                _cartState.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addProductToCart(product: Product) {
        viewModelScope.launch {
            try {
                productRepository.addProductToCart(product)
                getCartProducts()
            } catch (e: Exception) {
                _cartState.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
