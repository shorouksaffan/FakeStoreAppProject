package com.example.fakestoreappproject.di

import com.example.fakestoreappproject.ui.navigation.Destinations
import com.example.fakestoreappproject.ui.navigation.Navigator
import com.example.fakestoreappproject.ui.navigation.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    single<Navigator> { NavigatorImpl(startGraph = Destinations.MainGraph) }
}