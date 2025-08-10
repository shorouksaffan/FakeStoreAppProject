package com.example.fakestoreappproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.fakestoreappproject.ui.screens.ProductListScreen
import org.koin.compose.koinInject

@Composable
fun FakeStoreAppNavGraph(
    navigator: Navigator = koinInject(),
    navController: NavHostController = rememberNavController(),
) {

    ObserveAsEvents(navigator.navigationEvent) { event ->
        when (event) {
            is NavigationEvent.Navigate -> navController.navigate(
                route = event.destination, navOptions = event.navOptions
            )

            NavigationEvent.NavigateUp -> navController.navigateUp()
        }
    }

    NavHost(
        navController = navController,
        startDestination = navigator.startGraph
    ) {
        buildFakeStoreAppNavGraph()
    }
}

fun NavGraphBuilder.buildFakeStoreAppNavGraph() {
    navigation<Destinations.MainGraph>(startDestination = Destinations.ProductListScreen) {
        composable<Destinations.ProductListScreen> { ProductListScreen() }
    }
}
