package com.example.unitconverter.domain.model.unit

enum class AreaUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    SQUARE_METER("Square Meter", "m²", 1.0),
    SQUARE_KILOMETER("Square Kilometer", "km²", 1_000_000.0),
    SQUARE_CENTIMETER("Square Centimeter", "cm²", 0.0001),
    SQUARE_MILLIMETER("Square Millimeter", "mm²", 1e-6),
    HECTARE("Hectare", "ha", 10_000.0),
    ACRE("Acre", "ac", 4046.8564224),
    SQUARE_MILE("Square Mile", "mi²", 2_589_988.110336),
    SQUARE_YARD("Square Yard", "yd²", 0.83612736),
    SQUARE_FOOT("Square Foot", "ft²", 0.09290304),
    SQUARE_INCH("Square Inch", "in²", 0.00064516);
}