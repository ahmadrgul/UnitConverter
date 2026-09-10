package com.example.unitconverter.domain.model.unit

interface QuantityUnit {
    val unitName: String
    val symbol: String
    val baseMultiplier: Double
}