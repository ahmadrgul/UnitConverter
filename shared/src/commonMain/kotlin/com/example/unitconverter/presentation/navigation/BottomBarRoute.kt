package com.example.unitconverter.presentation.navigation

import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.home_icon
import com.example.unitconverter.presentation.home.HomeDestination
import org.jetbrains.compose.resources.DrawableResource

interface BottomBarRoute

data class NavItem(
    val label: String,
    val icon: DrawableResource,
    val route: BottomBarRoute
)

val navItems = listOf(
    NavItem("Home", Res.drawable.home_icon, route = HomeDestination)
)