package com.example.fakestoreappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.fakestoreappproject.data.paging.ProductPagingSource
import com.example.fakestoreappproject.data.repository.ProductRepository

class ProductListViewModel(private val productRepository: ProductRepository) : ViewModel() {
    val productsFlow = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        ProductPagingSource(productRepository, 10)
    }).flow.cachedIn(viewModelScope)
}

