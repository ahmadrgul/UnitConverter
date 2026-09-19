package com.example.unitconverter.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.unitconverter.domain.model.settings.SettingsItem
import com.example.unitconverter.presentation.settings.SettingsState


@Composable
fun SettingsGroup(
    state: SettingsState,
    title: String,
    items: List<SettingsItem>,
    toggleDarkTheme: () -> Unit,
    toggleHistory: () -> Unit,
    setAppLanguage: (String) -> Unit,
    setPrecision: (Int) -> Unit
){
    Column(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .dropShadow(
                    shape = RoundedCornerShape(12.dp),
                    shadow = Shadow(
                        color = Color.LightGray.copy(alpha = 0.2f),
                        radius = 5.dp,
                        spread = 0.dp
                    )
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEachIndexed { index, item ->
                SettingsItemCard(
                    state,
                    item,
                    {
                        DynamicItemRenderer(
                            id = item.id,
                            selectedLanguage = state.selectedLanguage,
                            selectedPrecision = state.selectedPrecision,
                            isDarkTheme = state.isDarkTheme,
                            languageOptions = state.languages,
                            precisionOptions = state.precisionValues,
                            isHistoryEnabled = state.isHistoryEnabled,
                            toggleDarkMode = toggleDarkTheme,
                            toggleHistory = toggleHistory,
                            setLanguage = setAppLanguage,
                            setPrecision = setPrecision
                        )
                    }
                )
                if (index < items.size - 1) {
                    HorizontalDivider(
                        thickness = 0.2.dp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}