package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fakestoreappproject.data.model.CartItem
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult
import com.example.fakestoreappproject.data.repository.ProductRepository
import com.example.fakestoreappproject.ui.navigation.Destinations
import com.example.fakestoreappproject.ui.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductDetailsState {
    data class Success(val product: Product) : ProductDetailsState()
    data class Error(val message: String) : ProductDetailsState()
    object Loading : ProductDetailsState()
}

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _productDetailsState =
        MutableStateFlow<ProductDetailsState>(ProductDetailsState.Loading)
    val state: StateFlow<ProductDetailsState> = _productDetailsState

    private val productId = savedStateHandle.toRoute<Destinations.ProductDetailScreen>().productId

    init {
        getProductById(productId)
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            _productDetailsState.value = ProductDetailsState.Loading
            when (val result = productRepository.getProductById(id)) {
                is ApiResult.Success -> {
                    _productDetailsState.value = ProductDetailsState.Success(result.data)
                }

                is ApiResult.Error -> {
                    _productDetailsState.value =
                        ProductDetailsState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }

    fun onAddToCart(product: Product) {
        viewModelScope.launch {
            val cartItem = productRepository.getCartItemByProductId(product.id)
            if (cartItem != null) {
                productRepository.updateCartItemQuantity(cartItem.product.id, cartItem.quantity + 1)
            } else {
                productRepository.addCartItem(
                    CartItem(
                        id = 0,
                        product = product,
                        quantity = 1,
                    )
                )
            }
            navigator.navigate(
                Destinations.CartScreen
            )
        }
    }
}