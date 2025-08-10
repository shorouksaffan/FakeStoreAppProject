package com.example.fakestoreappproject.di

import com.example.fakestoreappproject.data.local.AppDatabase
import com.example.fakestoreappproject.data.repository.ProductRepository
import com.example.fakestoreappproject.data.repository.ProductRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(AppDatabase::getInstance)
    single { get<AppDatabase>().cartDao() }
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
}