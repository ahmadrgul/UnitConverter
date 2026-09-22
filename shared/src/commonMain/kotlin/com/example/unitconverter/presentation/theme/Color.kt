package com.example.unitconverter.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PrimaryLight = Color(0xFF0d8ff9)
val PrimaryDark = Color(0xFF52B37D)
val BgAppLight = Color(0xFFfafcff)
val BgAppDark = Color(0xFF101726)
val SurfaceLight = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF1B2333)
val TextPrimaryLight = Color(0xFF14161F)
val TextPrimaryDark = Color(0xFFFFFFFF)

val LightColors = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = Color.White,
    background = BgAppLight,
    surface = SurfaceLight,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight
)

val DarkColors = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = Color.White,
    background = BgAppDark,
    surface = SurfaceDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark
)
val PaletteColors = listOf(
    Color(0xFF1FA971),
    Color(0xFF4C6FFF),
    Color(0xFF8B5CF6),
    Color(0xFFF5941D),
    Color(0xFFF0454F),
    Color(0xFF14B8A6),
    Color(0xFF2F80ED),
    Color(0xFFFF9F1C),
    Color(0xFF8B5CF6),
    Color(0xFF34A853),
    Color(0xFFF5941D)
)