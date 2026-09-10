package com.example.unitconverter.domain.model.unit

enum class TorqueUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    NEWTON_METER("Newton Meter", "N·m", 1.0),
    KILONEWTON_METER("Kilonewton Meter", "kN·m", 1000.0),
    NEWTON_CENTIMETER("Newton Centimeter", "N·cm", 0.01),
    POUND_FORCE_FOOT("Pound-force Foot", "lbf·ft", 1.3558179483314),
    POUND_FORCE_INCH("Pound-force Inch", "lbf·in", 0.1129848290276167),
    OUNCE_FORCE_INCH("Ounce-force Inch", "ozf·in", 0.007061551814226),
    KILOGRAM_FORCE_METER("Kilogram-force Meter", "kgf·m", 9.80665);
}