package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreappproject.data.model.CartItem
import com.example.fakestoreappproject.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CartState {
    data class Success(val cartItems: List<CartItem>) : CartState()
    data class Error(val message: String) : CartState()
    object Loading : CartState()
}

class CartViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _cartState = MutableStateFlow<CartState>(CartState.Loading)
    val cartState: StateFlow<CartState> = _cartState

    fun addCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            try {
                productRepository.addCartItem(cartItem)
                _cartState.value = CartState.Success(productRepository.getCartItems())
            } catch (e: Exception) {
                _cartState.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getCartItems() {
        viewModelScope.launch {
            _cartState.value = CartState.Loading
            try {
                val items = productRepository.getCartItems()
                _cartState.value = CartState.Success(items)
            } catch (e: Exception) {
                _cartState.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            try {
                productRepository.deleteCartItem(cartItem.product.id)
                _cartState.value = CartState.Success(productRepository.getCartItems())
            } catch (e: Exception) {
                _cartState.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            try {
                productRepository.clearCart()
                _cartState.value = CartState.Success(emptyList())
            } catch (e: Exception) {
                _cartState.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateCartItemQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            try {
                productRepository.updateCartItemQuantity(productId, quantity)
                _cartState.value = CartState.Success(productRepository.getCartItems())
            } catch (e: Exception) {
                _cartState.value = CartState.Error(e.message ?: "Unknown error")
            }
        }
    }

    suspend fun getTotalItemsInCart(): Int {
        return productRepository.getTotalItemsInCart()
    }

    suspend fun getTotalPriceInCart(): Double {
        return productRepository.getTotalPriceInCart()
    }
}
