package com.example.unitconverter.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.presentation.converter.ConverterDestination
import com.example.unitconverter.presentation.converter.converterScreen
import com.example.unitconverter.presentation.home.HomeDestination
import com.example.unitconverter.presentation.home.homeScreen
import com.example.unitconverter.presentation.navigation.components.BottomBar

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(
                currentDestination = navController.currentBackStackEntry?.destination,
                onNavigateToRoute = { navController.navigate(it) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeDestination,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            homeScreen(onNavigateToConverter = {
                navController.navigate(ConverterDestination(it))
            })

            converterScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }
    }
}