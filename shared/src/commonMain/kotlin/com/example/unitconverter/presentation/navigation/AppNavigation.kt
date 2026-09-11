package com.example.unitconverter.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.presentation.converter.ConverterDestination
import com.example.unitconverter.presentation.converter.converterScreen
import com.example.unitconverter.presentation.home.HomeDestination
import com.example.unitconverter.presentation.home.homeScreen
import com.example.unitconverter.presentation.navigation.components.BottomBar

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val isOnConverterScreen = currentDestination?.hasRoute<ConverterDestination>() == true

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = !isOnConverterScreen,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                BottomBar(
                    currentDestination = currentDestination,
                    onNavigateToRoute = { navController.navigate(it) }
                )
            }
        }
    ) { innerPadding ->

        val animatedPadding by animateDpAsState(
            targetValue = if (isOnConverterScreen) 0.dp else innerPadding.calculateBottomPadding(),
            animationSpec = (tween(durationMillis = 300)),
            label = "bottomPaddingAnimation"
        )

        NavHost(
            navController = navController,
            startDestination = HomeDestination,
            modifier = Modifier.padding(bottom = animatedPadding)
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