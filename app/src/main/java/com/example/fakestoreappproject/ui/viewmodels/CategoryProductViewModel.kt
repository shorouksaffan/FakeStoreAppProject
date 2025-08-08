package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult
import com.example.fakestoreappproject.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CategoryProductState {
    data class Success(val products: List<Product>) : CategoryProductState()
    data class Error(val message: String) : CategoryProductState()
    object Loading : CategoryProductState()
}

class CategoryProductViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val _categoryProductState = MutableStateFlow<CategoryProductState>(CategoryProductState.Loading)
    val categoryProductState: StateFlow<CategoryProductState> = _categoryProductState

    fun getProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            _categoryProductState.value = CategoryProductState.Loading
            when (val result = productRepository.getProductsByCategory(categoryId)) {
                is ApiResult.Success -> {
                    _categoryProductState.value = CategoryProductState.Success(result.data)
                }
                is ApiResult.Error -> {
                    _categoryProductState.value = CategoryProductState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }
}