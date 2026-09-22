package com.example.unitconverter.presentation.navigation

import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.history_filled_icon
import com.example.unitconverter.generated.resources.history_icon
import com.example.unitconverter.generated.resources.home_filled_icon
import com.example.unitconverter.generated.resources.home_icon
import com.example.unitconverter.generated.resources.settings_filled_icon
import com.example.unitconverter.generated.resources.settings_icon
import com.example.unitconverter.presentation.history.HistoryDestination
import com.example.unitconverter.presentation.home.HomeDestination
import com.example.unitconverter.presentation.settings.SettingsDestination
import org.jetbrains.compose.resources.DrawableResource

interface BottomBarRoute

data class NavItem(
    val label: String,
    val icon: DrawableResource,
    val selectedIcon: DrawableResource,
    val route: BottomBarRoute
)

val navItems = listOf(
    NavItem("Home", Res.drawable.home_icon, Res.drawable.home_filled_icon, route = HomeDestination),
    NavItem("History", Res.drawable.history_icon, Res.drawable.history_filled_icon, route = HistoryDestination),
    NavItem("Settings", Res.drawable.settings_icon, Res.drawable.settings_filled_icon, route = SettingsDestination)
)