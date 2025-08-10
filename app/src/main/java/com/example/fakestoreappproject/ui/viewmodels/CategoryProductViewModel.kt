package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult
import com.example.fakestoreappproject.data.repository.ProductRepository
import com.example.fakestoreappproject.ui.navigation.Destinations
import com.example.fakestoreappproject.ui.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CategoryProductState {
    data class Success(val products: List<Product>, val categoryName: String) :
        CategoryProductState()

    data class Error(val message: String) : CategoryProductState()
    object Loading : CategoryProductState()
}

class CategoryProductViewModel(
    private val productRepository: ProductRepository,
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _categoryProductState =
        MutableStateFlow<CategoryProductState>(CategoryProductState.Loading)
    val categoryProductState: StateFlow<CategoryProductState> = _categoryProductState

    val categoryId = savedStateHandle.toRoute<Destinations.CategoryProductScreen>().categoryId
    val categoryName = savedStateHandle.toRoute<Destinations.CategoryProductScreen>().categoryName

    init {
        getProductsByCategory(categoryId)
    }

    fun getProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            _categoryProductState.value = CategoryProductState.Loading
            when (val result = productRepository.getProductsByCategory(categoryId)) {
                is ApiResult.Success -> {
                    _categoryProductState.value =
                        CategoryProductState.Success(result.data, categoryName)
                }

                is ApiResult.Error -> {
                    _categoryProductState.value =
                        CategoryProductState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }

    fun onProductClick(product: Product) {
        viewModelScope.launch {
            navigator.navigate(
                Destinations.ProductDetailScreen(productId = product.id)
            )
        }
    }

    fun onAddToCart(product: Product) {
        //TODO
    }
}