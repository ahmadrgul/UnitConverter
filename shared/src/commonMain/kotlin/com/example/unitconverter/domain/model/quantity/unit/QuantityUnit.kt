package com.example.unitconverter.domain.model.quantity.unit

interface QuantityUnit {
    val unitName: String
    val symbol: String
    val baseMultiplier: Double
}