package com.example.unitconverter.domain

interface MeasurementUnit {
    val label: String
    val symbol: String
    val factorToBase: Double
}

enum class LengthUnit(
    override val label: String,
    override val symbol: String,
    override val factorToBase: Double,
) : MeasurementUnit {
    METER("Meter", "m", 1.0),
    KILOMETER("Kilometer", "km", 1000.0),
    CENTIMETER("Centimeter", "cm", 0.01),
    MILLIMETER("Millimeter", "mm", 0.001),
    MICROMETER("Micrometer", "µm", 0.000001),
    MILE("Mile", "mi", 1609.344),
    YARD("Yard", "yd", 0.9144),
    FOOT("Foot", "ft", 0.3048),
    INCH("Inch", "in", 0.0254)
}

enum class AreaUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    SQUARE_METER("Square Meter", "m²", 1.0), // BASE
    HECTARE("Hectare", "ha", 10000.0),
    ACRE("Acre", "ac", 4046.86)
}

enum class TimeUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    SECOND("Second", "s", 1.0), // BASE
    MINUTE("Minute", "min", 60.0),
    HOUR("Hour", "h", 3600.0)
}

enum class VolumeUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    LITER("Liter", "L", 1.0), // BASE
    MILLILITER("Milliliter", "mL", 0.001),
    GALLON_US("US Gallon", "gal", 3.78541)
}

enum class WeightUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    KILOGRAM("Kilogram", "kg", 1.0), // BASE
    GRAM("Gram", "g", 0.001),
    POUND("Pound", "lb", 0.453592)
}

enum class SpeedUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    METERS_PER_SECOND("Meters per Second", "m/s", 1.0), // BASE
    KILOMETERS_PER_HOUR("Kilometers per Hour", "km/h", 0.277778),
    MILES_PER_HOUR("Miles per Hour", "mph", 0.44704)
}

enum class EnergyUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    JOULE("Joule", "J", 1.0), // BASE
    KILOJOULE("Kilojoule", "kJ", 1000.0),
    CALORIE("Calorie", "cal", 4.184)
}

enum class PowerUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    WATT("Watt", "W", 1.0), // BASE
    KILOWATT("Kilowatt", "kW", 1000.0),
    HORSEPOWER("Horsepower", "hp", 745.7)
}

enum class TorqueUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    NEWTON_METER("Newton Meter", "N·m", 1.0), // BASE
    POUND_FOOT("Pound-Foot", "lb·ft", 1.35582)
}

enum class PressureUnit(
    override val label: String, override val symbol: String, override val factorToBase: Double
) : MeasurementUnit {
    PASCAL("Pascal", "Pa", 1.0), // BASE
    BAR("Bar", "bar", 100000.0),
    PSI("Pounds per Sq Inch", "psi", 6894.76)
}