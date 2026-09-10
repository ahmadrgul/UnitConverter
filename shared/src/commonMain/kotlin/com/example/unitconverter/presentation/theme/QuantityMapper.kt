package com.example.unitconverter.presentation.theme

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
import com.example.unitconverter.generated.resources.weight_icon
import com.example.unitconverter.generated.resources.wrench_icon
import org.jetbrains.compose.resources.DrawableResource

fun getQuantityColor(index: Int): Color {
    val totalColors = PaletteColors.size
    return PaletteColors[index % totalColors]
}

fun getQuantityIcon(id: String): DrawableResource {
    return when (id) {
        "length" -> Res.drawable.ruler_icon
        "area" -> Res.drawable.area_icon
        "time" -> Res.drawable.clock_icon
        "volume" -> Res.drawable.jar_icon
        "weight" -> Res.drawable.weight_icon
        "speed" -> Res.drawable.speed_icon
        "energy" -> Res.drawable.flash_icon
        "power" -> Res.drawable.power_icon
        "torque" -> Res.drawable.wrench_icon
        "pressure" -> Res.drawable.pressure_icon
        else -> Res.drawable.ruler_icon
    }
}