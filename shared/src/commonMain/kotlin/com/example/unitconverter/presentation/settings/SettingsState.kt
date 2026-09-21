package com.example.unitconverter.presentation.settings

import com.example.unitconverter.presentation.settings.model.SettingsSectionUi

data class SettingsState(
    val sections: List<SettingsSectionUi> = emptyList()
)