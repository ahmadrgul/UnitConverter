package com.example.unitconverter.navigation

import kotlinx.serialization.Serializable

interface BottomBarRoute

@Serializable
object Home: BottomBarRoute

@Serializable
object History: BottomBarRoute

@Serializable
object Settings: BottomBarRoute

@Serializable
data class Calculator (val routeId: String)