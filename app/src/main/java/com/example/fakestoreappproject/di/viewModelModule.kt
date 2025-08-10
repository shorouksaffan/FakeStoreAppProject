package com.example.fakestoreappproject.di

import com.example.fakestoreappproject.ui.viewmodels.CartViewModel
import com.example.fakestoreappproject.ui.viewmodels.CategoryProductViewModel
import com.example.fakestoreappproject.ui.viewmodels.CategoryViewModel
import com.example.fakestoreappproject.ui.viewmodels.ProductDetailViewModel
import com.example.fakestoreappproject.ui.viewmodels.ProductListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::CartViewModel)
    viewModelOf(::CategoryProductViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::ProductDetailViewModel)
    viewModelOf(::ProductListViewModel)
}