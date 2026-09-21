package com.example.unitconverter.presentation.settings.model

data class SettingsSectionUi(
    val id: SectionId,
    val title: String,
    val items: List<SettingsItemUi>
)