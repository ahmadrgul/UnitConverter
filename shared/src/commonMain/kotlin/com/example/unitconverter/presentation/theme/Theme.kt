package com.example.unitconverter.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun UnitConverterTheme(
    isDark: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isDark) DarkColors else LightColors,
        typography = appTypography(),
        content = content
    )
}