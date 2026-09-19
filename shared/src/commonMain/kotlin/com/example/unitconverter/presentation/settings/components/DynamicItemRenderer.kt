package com.example.unitconverter.presentation.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.chevron_right_icon
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DynamicItemRenderer(
    id: String,
    isDarkTheme: Boolean,
    isHistoryEnabled: Boolean,
    selectedPrecision: Int,
    selectedLanguage: String,
    toggleDarkMode: () -> Unit,
    languageOptions: List<String>,
    setLanguage: (String) -> Unit,
    toggleHistory: () -> Unit,
    precisionOptions: List<Int>,
    setPrecision: (Int) -> Unit
){
    when (id) {
        "darkMode" -> CustomSwitch(
            isChecked = isDarkTheme,
            onCheckedChange = toggleDarkMode
        )

        "language" -> SettingsOptionWithDropdown(
            selectedOption = selectedLanguage,
            options = languageOptions,
            onOptionSelect = setLanguage
        )

        "history" -> CustomSwitch(
            isChecked = isHistoryEnabled,
            onCheckedChange = toggleHistory
        )

        "precision" -> SettingsOptionWithDropdown(
            selectedOption = selectedPrecision.toString(),
            options = precisionOptions as List<String>,
            onOptionSelect = { setPrecision(it.toInt()) }
        )

        "about" -> {
            Row(
                modifier = Modifier.clickable(
                    onClick = {}
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Version 1.0.0",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    painter = painterResource(Res.drawable.chevron_right_icon),
                    contentDescription = "Chevron Right",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
            }
        }

        else -> {
            Icon(
                painter = painterResource(Res.drawable.chevron_right_icon),
                contentDescription = "Chevron Right",
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )
        }

    }
}