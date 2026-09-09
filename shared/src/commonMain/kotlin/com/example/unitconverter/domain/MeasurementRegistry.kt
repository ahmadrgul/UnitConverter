package com.example.unitconverter.domain

import androidx.compose.ui.graphics.Color
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.area_icon
import com.example.unitconverter.generated.resources.clock_icon
import com.example.unitconverter.generated.resources.flash_icon
import com.example.unitconverter.generated.resources.jar_icon
import com.example.unitconverter.generated.resources.power_icon
import com.example.unitconverter.generated.resources.pressure_icon
import com.example.unitconverter.generated.resources.ruler_icon
import com.example.unitconverter.generated.resources.speed_icon
import com.example.unitconverter.generated.resources.thermostat_icon
import com.example.unitconverter.generated.resources.weight_icon
import com.example.unitconverter.generated.resources.wrench_icon

object MeasurementRegistry {
    val allMeasurements: List<Measurement<MeasurementUnit>> = listOf(
        Measurement(
            "calculator/length",
            "Length", Res.drawable.ruler_icon,
            Color(0xFF1FA971),
            MeasurementCategory.GENERAL,
            LengthUnit.entries
        ),

        Measurement("calculator/area", "Area", Res.drawable.area_icon, Color(0xFF4C6FFF), MeasurementCategory.GENERAL, AreaUnit.entries),
        Measurement("calculator/time", "Time", Res.drawable.clock_icon, Color(0xFF8B5CF6), MeasurementCategory.GENERAL, TimeUnit.entries),
        Measurement("calculator/volume", "Volume", Res.drawable.jar_icon, Color(0xFFF5941D), MeasurementCategory.GENERAL, VolumeUnit.entries),
//        Measurement("calculator/temperature", "Temperature", Res.drawable.thermostat_icon, Color(0xFFF0454F), MeasurementCategory.GENERAL, TemperatureUnit.entries),
        Measurement("calculator/weight", "Weight", Res.drawable.weight_icon, Color(0xFF14B8A6), MeasurementCategory.GENERAL, WeightUnit.entries),
        Measurement("calculator/speed", "Speed", Res.drawable.speed_icon, Color(0xFF2F80ED), MeasurementCategory.GENERAL, SpeedUnit.entries),
        Measurement("calculator/energy", "Energy", Res.drawable.flash_icon, Color(0xFFFF9F1C), MeasurementCategory.GENERAL, EnergyUnit.entries),

        Measurement("calculator/power", "Power", Res.drawable.power_icon, Color(0xFF8B5CF6), MeasurementCategory.MECHANICAL, PowerUnit.entries),
        Measurement("calculator/torque", "Torque", Res.drawable.wrench_icon, Color(0xFF34A853), MeasurementCategory.MECHANICAL, TorqueUnit.entries),
        Measurement("calculator/pressure", "Pressure", Res.drawable.pressure_icon, Color(0xFFF5941D), MeasurementCategory.MECHANICAL, PressureUnit.entries)
    )

    val groupedMeasurements = allMeasurements.groupBy { it.category }

    fun getById(routeId: String): Measurement<MeasurementUnit>? {
        return allMeasurements.find { it.routeId == routeId }
    }
}