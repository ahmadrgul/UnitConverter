package com.example.unitconverter.domain.model.unit

enum class LengthUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    METER("Meter", "m", 1.0),
    KILOMETER("Kilometer", "km", 1000.0),
    CENTIMETER("Centimeter", "cm", 0.01),
    MILLIMETER("Millimeter", "mm", 0.001),
    MICROMETER("Micrometer", "µm", 1e-6),
    NANOMETER("Nanometer", "nm", 1e-9),
    MILE("Mile", "mi", 1609.344),
    YARD("Yard", "yd", 0.9144),
    FOOT("Foot", "ft", 0.3048),
    INCH("Inch", "in", 0.0254),
    NAUTICAL_MILE("Nautical Mile", "NM", 1852.0);
}