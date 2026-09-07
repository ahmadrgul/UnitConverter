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
enum class Measurement {
    LENGTH,
    AREA,
    TIME,
    VOLUME,
    TEMPERATURE,
    WEIGHT,
    SPEED,
    ENERGY,
    POWER,
    TORQUE,
    PRESSURE,
}

@Serializable
data class Calculator (val measurement: Measurement)