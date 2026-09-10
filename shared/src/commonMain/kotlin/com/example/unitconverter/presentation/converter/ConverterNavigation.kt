package com.example.unitconverter.presentation.converter

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class ConverterDestination(val quantityId: String)

fun NavGraphBuilder.converterScreen(onNavigateBack: () -> Unit) {
    composable<ConverterDestination> { backStackEntry ->
        val dest = backStackEntry.toRoute<ConverterDestination>()
        ConverterRoute(
            quantityId = dest.quantityId,
            onNavigateBack = onNavigateBack
        )
    }
}