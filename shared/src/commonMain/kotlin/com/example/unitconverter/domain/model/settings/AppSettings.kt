package com.example.unitconverter.domain.model.settings

data class AppSettings(
    val darkMode: Boolean = false,
    val saveHistory: Boolean = true,
    val language: AppLanguage = AppLanguage.ENGLISH,
    val decimalPrecision: Int = 2
)