package com.example.unitconverter.domain.model.unit

enum class PressureUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    PASCAL("Pascal", "Pa", 1.0),
    KILOPASCAL("Kilopascal", "kPa", 1000.0),
    MEGAPASCAL("Megapascal", "MPa", 1_000_000.0),
    BAR("Bar", "bar", 100_000.0),
    MILLIBAR("Millibar", "mbar", 100.0),
    STANDARD_ATMOSPHERE("Standard Atmosphere", "atm", 101_325.0),
    POUND_PER_SQUARE_INCH("Pound per Square Inch", "psi", 6894.757293168),
    POUND_PER_SQUARE_FOOT("Pound per Square Foot", "psf", 47.8802589803),
    TORR("Torr", "Torr", 133.32236842105),
    MILLIMETER_OF_MERCURY("Millimeter of Mercury", "mmHg", 133.322387415),
    INCH_OF_MERCURY("Inch of Mercury", "inHg", 3386.389),
    INCH_OF_WATER("Inch of Water", "inH₂O", 249.082);
}