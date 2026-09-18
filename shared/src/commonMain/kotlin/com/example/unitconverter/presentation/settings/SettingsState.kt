package com.example.unitconverter.presentation.settings

import com.example.unitconverter.domain.model.settings.SettingsItem

data class SettingsState(
    val items: Map<String, List<SettingsItem>>,
    val isDarkTheme: Boolean,
    val languages: List<String>,
    val selectedLanguage: String,
    val isHistoryEnabled: Boolean,
    val precisionValues: List<Int>,
    val selectedPrecision: Int
)