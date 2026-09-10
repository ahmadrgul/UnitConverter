package com.example.unitconverter.domain.model.unit

enum class PowerUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    WATT("Watt", "W", 1.0),
    KILOWATT("Kilowatt", "kW", 1000.0),
    MEGAWATT("Megawatt", "MW", 1_000_000.0),
    MILLIWATT("Milliwatt", "mW", 0.001),
    HORSEPOWER_MECHANICAL("Horsepower (Mechanical)", "hp", 745.6998715822702),
    HORSEPOWER_METRIC("Horsepower (Metric)", "PS", 735.49875),
    BTU_PER_HOUR("BTU per Hour", "BTU/h", 0.293071070172222),
    FOOT_POUND_PER_MINUTE("Foot-pound per Minute", "ft-lb/min", 0.022596965805523);
}