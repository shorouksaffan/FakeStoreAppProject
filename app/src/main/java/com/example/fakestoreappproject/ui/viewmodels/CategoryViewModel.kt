package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreappproject.data.model.Category
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult
import com.example.fakestoreappproject.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CategoryState {
    data class Success(val categories: List<Category>) : CategoryState()
    data class Error(val message: String) : CategoryState()
    object Loading : CategoryState()
}

class CategoryViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val _categoryState = MutableStateFlow<CategoryState>(CategoryState.Loading)
    val categoryState: StateFlow<CategoryState> = _categoryState

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            _categoryState.value = CategoryState.Loading
            when (val result = productRepository.getCategories()) {
                is ApiResult.Success -> {
                    _categoryState.value = CategoryState.Success(result.data)
                }
                is ApiResult.Error -> {
                    _categoryState.value = CategoryState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }

    fun onCategoryClick(category: Category) {
        //TODO
    }
}
