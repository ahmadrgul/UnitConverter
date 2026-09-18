package com.example.unitconverter.presentation.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.unitconverter.presentation.navigation.BottomBarRoute
import kotlinx.serialization.Serializable

@Serializable
object SettingsDestination: BottomBarRoute

fun NavGraphBuilder.settingsScreen(){
    composable<SettingsDestination> { SettingsRoute() }
}