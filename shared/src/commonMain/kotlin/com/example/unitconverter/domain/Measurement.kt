package com.example.unitconverter.domain

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

enum class MeasurementCategory(val displayName: String) {
    GENERAL("Everyday Units"),
    MECHANICAL("Mechanical Units")
}

class Measurement<T : MeasurementUnit>(
    val routeId: String,
    val label: String,
    val icon: DrawableResource,
    val color: Color,
    val category: MeasurementCategory,
    val units: List<T>
) {
    fun convert(value: Double, from: T, to: T): Double {
        val valueInBase = value * from.factorToBase
        return valueInBase / to.factorToBase
    }
}