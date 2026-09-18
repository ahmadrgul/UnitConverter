package com.example.unitconverter.domain.model.settings

data class SettingsItem(
    val id: String,
    val title: String,
    val subtitle: String? = null,
)