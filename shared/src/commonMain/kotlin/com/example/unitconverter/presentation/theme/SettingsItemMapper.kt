package com.example.unitconverter.presentation.theme

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
import com.example.unitconverter.presentation.settings.model.SettingId
import org.jetbrains.compose.resources.DrawableResource

fun getSettingsItemIcon(id: SettingId): DrawableResource {
    return when (id) {
        SettingId.THEME -> Res.drawable.moon_icon
        SettingId.LANGUAGE -> Res.drawable.globe_icon
        SettingId.HISTORY -> Res.drawable.history_icon
        SettingId.PRECISION -> Res.drawable.decimal_icon
        SettingId.HELP -> Res.drawable.question_icon
        SettingId.SUPPORT -> Res.drawable.support_icon
        SettingId.RATE -> Res.drawable.star_icon
        SettingId.SHARE -> Res.drawable.share_icon
        SettingId.ABOUT -> Res.drawable.about_icon
    }
}

fun getSettingsItemIconColor(id: SettingId): Color {
    return when (id) {
        SettingId.THEME -> PrimaryLight
        SettingId.LANGUAGE -> PrimaryLight
        SettingId.HISTORY -> PaletteColors[1]
        SettingId.PRECISION -> PaletteColors[2]
        SettingId.HELP -> PaletteColors[3]
        SettingId.SUPPORT -> PaletteColors[4]
        SettingId.RATE -> PaletteColors[5]
        SettingId.SHARE -> PaletteColors[6]
        SettingId.ABOUT -> PaletteColors[7]
    }
}