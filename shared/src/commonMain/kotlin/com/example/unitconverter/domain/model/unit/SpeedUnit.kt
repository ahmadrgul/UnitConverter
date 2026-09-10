package com.example.unitconverter.domain.model.unit

enum class SpeedUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    METER_PER_SECOND("Meter per second", "m/s", 1.0),
    KILOMETER_PER_HOUR("Kilometer per hour", "km/h", 1000.0 / 3600.0),
    MILE_PER_HOUR("Mile per hour", "mph", 0.44704),
    FOOT_PER_SECOND("Foot per second", "ft/s", 0.3048),
    KNOT("Knot", "kn", 0.5144444444444445),
    MACH("Mach", "M", 340.3); // Mach 1 at standard sea level conditions
}