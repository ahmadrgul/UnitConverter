package com.example.unitconverter.domain.model

import com.example.unitconverter.domain.model.unit.AreaUnit
import com.example.unitconverter.domain.model.unit.EnergyUnit
import com.example.unitconverter.domain.model.unit.LengthUnit
import com.example.unitconverter.domain.model.unit.PowerUnit
import com.example.unitconverter.domain.model.unit.PressureUnit
import com.example.unitconverter.domain.model.unit.QuantityUnit
import com.example.unitconverter.domain.model.unit.SpeedUnit
import com.example.unitconverter.domain.model.unit.TimeUnit
import com.example.unitconverter.domain.model.unit.TorqueUnit
import com.example.unitconverter.domain.model.unit.VolumeUnit
import com.example.unitconverter.domain.model.unit.WeightUnit

object QuantityRegistry {
    val length: Quantity<QuantityUnit> = Quantity(
        id = "length",
        quantityName = "Length",
        category = QuantityCategory.COMMON,
        availableUnits = LengthUnit.entries,
        baseUnit = LengthUnit.METER,
        defaultFrom = LengthUnit.METER,
        defaultTo = LengthUnit.KILOMETER,
        popularUnits = listOf(LengthUnit.METER, LengthUnit.MILE, LengthUnit.FOOT, LengthUnit.INCH)
    )

    val area: Quantity<QuantityUnit> = Quantity(
        id = "area",
        quantityName = "Area",
        category = QuantityCategory.COMMON,
        availableUnits = AreaUnit.entries,
        baseUnit = AreaUnit.SQUARE_METER,
        defaultFrom = AreaUnit.SQUARE_METER,
        defaultTo = AreaUnit.SQUARE_FOOT,
        popularUnits = listOf(
            AreaUnit.SQUARE_METER,
            AreaUnit.SQUARE_FOOT,
            AreaUnit.ACRE,
            AreaUnit.HECTARE
        )
    )

    val time: Quantity<QuantityUnit> = Quantity(
        id = "time",
        quantityName = "Time",
        category = QuantityCategory.COMMON,
        availableUnits = TimeUnit.entries,
        baseUnit = TimeUnit.SECOND,
        defaultFrom = TimeUnit.HOUR,
        defaultTo = TimeUnit.MINUTE,
        popularUnits = listOf(TimeUnit.SECOND, TimeUnit.MINUTE, TimeUnit.HOUR, TimeUnit.DAY)
    )

    val volume: Quantity<QuantityUnit> = Quantity(
        id = "volume",
        quantityName = "Volume",
        category = QuantityCategory.COMMON,
        availableUnits = VolumeUnit.entries,
        baseUnit = VolumeUnit.LITER,
        defaultFrom = VolumeUnit.LITER,
        defaultTo = VolumeUnit.MILLILITER,
        popularUnits = listOf(
            VolumeUnit.LITER,
            VolumeUnit.MILLILITER,
            VolumeUnit.US_GALLON,
            VolumeUnit.CUBIC_METER
        )
    )

    val weight: Quantity<QuantityUnit> = Quantity(
        id = "weight",
        quantityName = "Weight",
        category = QuantityCategory.COMMON,
        availableUnits = WeightUnit.entries,
        baseUnit = WeightUnit.GRAM,
        defaultFrom = WeightUnit.KILOGRAM,
        defaultTo = WeightUnit.POUND,
        popularUnits = listOf(
            WeightUnit.GRAM,
            WeightUnit.KILOGRAM,
            WeightUnit.POUND,
            WeightUnit.OUNCE
        )
    )

    val speed: Quantity<QuantityUnit> = Quantity(
        id = "speed",
        quantityName = "Speed",
        category = QuantityCategory.COMMON,
        availableUnits = SpeedUnit.entries,
        baseUnit = SpeedUnit.METER_PER_SECOND,
        defaultFrom = SpeedUnit.KILOMETER_PER_HOUR,
        defaultTo = SpeedUnit.MILE_PER_HOUR,
        popularUnits = listOf(
            SpeedUnit.METER_PER_SECOND,
            SpeedUnit.KILOMETER_PER_HOUR,
            SpeedUnit.MILE_PER_HOUR,
            SpeedUnit.KNOT
        )
    )

    val energy: Quantity<QuantityUnit> = Quantity(
        id = "energy",
        quantityName = "Energy",
        category = QuantityCategory.COMMON,
        availableUnits = EnergyUnit.entries,
        baseUnit = EnergyUnit.JOULE,
        defaultFrom = EnergyUnit.KILOCALORIE,
        defaultTo = EnergyUnit.KILOWATT_HOUR,
        popularUnits = listOf(
            EnergyUnit.JOULE,
            EnergyUnit.KILOCALORIE,
            EnergyUnit.KILOWATT_HOUR,
            EnergyUnit.BRITISH_THERMAL_UNIT
        )
    )

    val power: Quantity<QuantityUnit> = Quantity(
        id = "power",
        quantityName = "Power",
        category = QuantityCategory.MECHANICAL,
        availableUnits = PowerUnit.entries,
        baseUnit = PowerUnit.WATT,
        defaultFrom = PowerUnit.WATT,
        defaultTo = PowerUnit.KILOWATT,
        popularUnits = listOf(
            PowerUnit.WATT,
            PowerUnit.KILOWATT,
            PowerUnit.HORSEPOWER_MECHANICAL,
            PowerUnit.MEGAWATT
        )
    )

    val torque: Quantity<QuantityUnit> = Quantity(
        id = "torque",
        quantityName = "Torque",
        category = QuantityCategory.MECHANICAL,
        availableUnits = TorqueUnit.entries,
        baseUnit = TorqueUnit.NEWTON_METER,
        defaultFrom = TorqueUnit.NEWTON_METER,
        defaultTo = TorqueUnit.POUND_FORCE_FOOT,
        popularUnits = listOf(
            TorqueUnit.NEWTON_METER,
            TorqueUnit.POUND_FORCE_FOOT,
            TorqueUnit.POUND_FORCE_INCH,
            TorqueUnit.KILOGRAM_FORCE_METER
        )
    )

    val pressure: Quantity<QuantityUnit> = Quantity(
        id = "pressure",
        quantityName = "Pressure",
        category = QuantityCategory.MECHANICAL,
        availableUnits = PressureUnit.entries,
        baseUnit = PressureUnit.PASCAL,
        defaultFrom = PressureUnit.BAR,
        defaultTo = PressureUnit.POUND_PER_SQUARE_INCH,
        popularUnits = listOf(
            PressureUnit.PASCAL,
            PressureUnit.BAR,
            PressureUnit.POUND_PER_SQUARE_INCH,
            PressureUnit.STANDARD_ATMOSPHERE
        )
    )

    private val allQuantities = listOf(
        length,
        area,
        time,
        volume,
        weight,
        speed,
        energy,
        power,
        torque,
        pressure
    )

    val groupedQuantities = allQuantities.groupBy { it.category }

    fun getQuantityById(id: String): Quantity<QuantityUnit>? {
        return allQuantities.find { it.id == id }
    }
}