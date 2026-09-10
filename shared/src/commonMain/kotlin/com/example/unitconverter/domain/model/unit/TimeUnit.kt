package com.example.unitconverter.domain.model.unit

enum class TimeUnit(
    override val unitName: String,
    override val symbol: String,
    override val baseMultiplier: Double
) : QuantityUnit {
    SECOND("Second", "s", 1.0),
    MILLISECOND("Millisecond", "ms", 0.001),
    MICROSECOND("Microsecond", "µs", 1e-6),
    NANOSECOND("Nanosecond", "ns", 1e-9),
    MINUTE("Minute", "min", 60.0),
    HOUR("Hour", "h", 3600.0),
    DAY("Day", "d", 86_400.0),
    WEEK("Week", "wk", 604_800.0),
    MONTH("Month", "mo", 2_629_800.0), // Based on average month (365.25 days / 12)
    YEAR("Year", "yr", 31_557_600.0); // Based on Julian year (365.25 days)
}