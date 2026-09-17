package com.example.unitconverter.presentation.history

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.unitconverter.presentation.navigation.BottomBarRoute
import kotlinx.serialization.Serializable

@Serializable
object HistoryDestination: BottomBarRoute

fun NavGraphBuilder.historyScreen(){
    return composable<HistoryDestination> { HistoryRoute() }
}