package com.example.fakestoreappproject.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination

@Serializable
sealed interface Graph : Destination

@Serializable
sealed interface Destinations : Graph {

    @Serializable
    data object MainGraph : Graph

    @Serializable
    data object ProductListScreen : Destination
}