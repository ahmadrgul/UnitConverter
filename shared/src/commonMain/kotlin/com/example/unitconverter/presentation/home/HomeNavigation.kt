package com.example.unitconverter.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.unitconverter.presentation.navigation.BottomBarRoute
import kotlinx.serialization.Serializable

@Serializable
object HomeDestination : BottomBarRoute


fun NavGraphBuilder.homeScreen(onNavigateToConverter: (String) -> Unit) {
    composable<HomeDestination> { HomeRoute(onNavigateToConverter) }
}