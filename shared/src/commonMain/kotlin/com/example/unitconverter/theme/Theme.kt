package com.example.unitconverter.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.inter_bold
import com.example.unitconverter.generated.resources.inter_medium
import com.example.unitconverter.generated.resources.inter_regular
import com.example.unitconverter.generated.resources.inter_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun appTypography(): Typography {
    val inter = FontFamily(
        Font(Res.font.inter_regular, FontWeight.Normal),
        Font(Res.font.inter_medium, FontWeight.Medium),
        Font(Res.font.inter_semibold, FontWeight.SemiBold),
        Font(Res.font.inter_bold, FontWeight.Bold),
    )
    return remember(inter){
        Typography(
            displayLarge = TextStyle(fontFamily = inter, fontSize = 30.sp, fontWeight = FontWeight.Bold),
            headlineLarge = TextStyle(fontFamily = inter, fontSize = 22.sp, fontWeight = FontWeight.Bold),
            titleLarge = TextStyle(fontFamily = inter, fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
            bodyLarge = TextStyle(fontFamily = inter, fontSize = 16.sp, fontWeight = FontWeight.Normal),
            bodyMedium = TextStyle(fontFamily = inter, fontSize = 14.sp, fontWeight = FontWeight.Normal),
            labelLarge = TextStyle(fontFamily = inter, fontSize = 14.sp, fontWeight = FontWeight.Medium),
            labelSmall = TextStyle(fontFamily = inter, fontSize = 12.sp, fontWeight = FontWeight.Normal),
        )
    }
}

val PrimaryLight = Color(0xFF2F6FED)
val BgAppLight = Color(0xFFfafcff)
val SurfaceLight = Color(0xFFFFFFFF)
val TextPrimaryLight = Color(0xFF14161F)

private val LightColors = lightColorScheme(
    primary = PrimaryLight,
    background = BgAppLight,
    surface = SurfaceLight,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight
)

@Composable
fun UnitConverterTheme(content: @Composable () -> Unit) {
    MaterialTheme (
        colorScheme = LightColors,
        typography = appTypography(),
        content = content
    )
}