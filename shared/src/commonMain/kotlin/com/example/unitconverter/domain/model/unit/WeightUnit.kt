package com.example.unitconverter.domain.model.unit

enum class WeightUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    GRAM("Gram", "g", 1.0),
    KILOGRAM("Kilogram", "kg", 1000.0),
    MILLIGRAM("Milligram", "mg", 0.001),
    MICROGRAM("Microgram", "µg", 1e-6),
    METRIC_TON("Metric Ton", "t", 1_000_000.0),
    POUND("Pound", "lb", 453.59237),
    OUNCE("Ounce", "oz", 28.349523125),
    STONE("Stone", "st", 6350.29318),
    SHORT_TON("US Short Ton", "us t", 907_184.74),
    LONG_TON("UK Long Ton", "uk t", 1_016_046.9088);
}