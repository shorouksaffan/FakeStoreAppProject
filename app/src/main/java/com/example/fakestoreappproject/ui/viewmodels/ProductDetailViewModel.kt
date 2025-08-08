package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult
import com.example.fakestoreappproject.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductDetailsState {
    data class Success(val product: Product) : ProductDetailsState()
    data class Error(val message: String) : ProductDetailsState()
    object Loading : ProductDetailsState()
}

class ProductDetailViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val _productDetailsState = MutableStateFlow<ProductDetailsState>(ProductDetailsState.Loading)
    val state: StateFlow<ProductDetailsState> = _productDetailsState

    fun getProductById(id: Int) {
        viewModelScope.launch {
            _productDetailsState.value = ProductDetailsState.Loading
            when (val result = productRepository.getProductById(id)) {
                is ApiResult.Success -> {
                    _productDetailsState.value = ProductDetailsState.Success(result.data)
                }
                is ApiResult.Error -> {
                    _productDetailsState.value = ProductDetailsState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }
}