package com.example.unitconverter.domain.model.unit

enum class VolumeUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    LITER("Liter", "L", 1.0),
    MILLILITER("Milliliter", "mL", 0.001),
    CUBIC_METER("Cubic Meter", "m³", 1000.0),
    CUBIC_CENTIMETER("Cubic Centimeter", "cm³", 0.001),
    US_GALLON("US Gallon", "gal", 3.785411784),
    US_QUART("US Quart", "qt", 0.946352946),
    US_PINT("US Pint", "pt", 0.473176473),
    US_CUP("US Cup", "cup", 0.2365882365),
    US_FLUID_OUNCE("US Fluid Ounce", "fl oz", 0.0295735295625),
    IMPERIAL_GALLON("Imperial Gallon", "imp gal", 4.54609),
    IMPERIAL_FLUID_OUNCE("Imperial Fluid Ounce", "imp fl oz", 0.0284130625),
    CUBIC_FOOT("Cubic Foot", "ft³", 28.316846592),
    CUBIC_INCH("Cubic Inch", "in³", 0.016387064);
}