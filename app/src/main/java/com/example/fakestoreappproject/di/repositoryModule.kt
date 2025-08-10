package com.example.fakestoreappproject.di

import com.example.fakestoreappproject.data.repository.ProductRepository
import com.example.fakestoreappproject.data.repository.ProductRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
}