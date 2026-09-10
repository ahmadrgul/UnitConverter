package com.example.unitconverter.domain.model.unit

enum class EnergyUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    JOULE("Joule", "J", 1.0),
    KILOJOULE("Kilojoule", "kJ", 1000.0),
    GRAM_CALORIE("Gram Calorie", "cal", 4.184),
    KILOCALORIE("Kilocalorie", "kcal", 4184.0),
    WATT_HOUR("Watt-hour", "Wh", 3600.0),
    KILOWATT_HOUR("Kilowatt-hour", "kWh", 3_600_000.0),
    ELECTRONVOLT("Electronvolt", "eV", 1.602176634e-19),
    BRITISH_THERMAL_UNIT("British Thermal Unit", "BTU", 1055.05585),
    US_THERM("US Therm", "thm", 105_480_400.0),
    FOOT_POUND("Foot-pound", "ft-lb", 1.3558179483314);
}