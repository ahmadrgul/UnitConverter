package com.example.unitconverter.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.about_icon
import com.example.unitconverter.generated.resources.decimal_icon
import com.example.unitconverter.generated.resources.globe_icon
import com.example.unitconverter.generated.resources.history_icon
import com.example.unitconverter.generated.resources.moon_icon
import com.example.unitconverter.generated.resources.question_icon
import com.example.unitconverter.generated.resources.share_icon
import com.example.unitconverter.generated.resources.star_icon
import com.example.unitconverter.generated.resources.support_icon
import org.jetbrains.compose.resources.DrawableResource

fun getSettingsItemIcon(id: String): DrawableResource {
    return when (id) {
        "darkMode" -> Res.drawable.moon_icon
        "language" -> Res.drawable.globe_icon
        "history" -> Res.drawable.history_icon
        "precision" -> Res.drawable.decimal_icon
        "faq" -> Res.drawable.question_icon
        "support" -> Res.drawable.support_icon
        "rating" -> Res.drawable.star_icon
        "share" -> Res.drawable.share_icon
        "about" -> Res.drawable.about_icon
        else -> Res.drawable.moon_icon
    }
}

fun getSettingsItemIconColor(id: String): Color {
    return when (id) {
        "darkMode" -> PrimaryLight
        "language" -> PrimaryLight
        "history" -> PaletteColors[1]
        "precision" -> PaletteColors[2]
        "faq" -> PaletteColors[3]
        "support" -> PaletteColors[4]
        "rating" -> PaletteColors[5]
        "share" -> PaletteColors[6]
        "about" -> PaletteColors[7]
        else -> Color.Gray
    }
}