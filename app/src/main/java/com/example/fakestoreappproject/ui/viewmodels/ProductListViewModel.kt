package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.paging.ProductPagingSource
import com.example.fakestoreappproject.data.repository.ProductRepository
import com.example.fakestoreappproject.ui.navigation.Destinations
import com.example.fakestoreappproject.ui.navigation.Navigator
import com.example.fakestoreappproject.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(private val productRepository: ProductRepository, private val navigator: Navigator) :
    ViewModel() {

    val productsFlow: Flow<PagingData<Product>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { ProductPagingSource(productRepository, 10) }).flow.cachedIn(
        viewModelScope
    )

    private val _uiState = MutableStateFlow<Resource<Flow<PagingData<Product>>>>(Resource.Loading())
    val uiState: StateFlow<Resource<Flow<PagingData<Product>>>> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = Resource.Loading()
            try {
                _uiState.value = Resource.Success(productsFlow)
            } catch (e: Exception) {
                _uiState.value = Resource.Error(e.message ?: "Failed to load initial data")
            }
        }
    }

    fun handleError(error: String) {
        _uiState.value = Resource.Error(error)
    }

    fun resetErrorState() {
        _uiState.value = Resource.Loading()
    }

    fun onProductClick(product: Product) {
        //TODO
    }

    fun onAddToCart(product: Product) {
        //TODO
    }

    fun onCategoriesClick() {
        //TODO
    }
}