package com.example.fakestoreappproject.di

import org.koin.dsl.module

val appModule = module {
    includes(
        navigationModule,
        dataModule,
        repositoryModule,
        viewModelModule,
        networkModule
    )
}