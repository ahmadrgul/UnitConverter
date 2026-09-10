package com.example.unitconverter.domain.model

import com.example.unitconverter.domain.model.unit.QuantityUnit

data class Quantity<T : QuantityUnit>(
    val id: String,
    val quantityName: String,
    val category: QuantityCategory,
    val availableUnits: List<T>,
    val baseUnit: T,
    val defaultFrom: T,
    val defaultTo: T,
    val popularUnits: List<T>
)