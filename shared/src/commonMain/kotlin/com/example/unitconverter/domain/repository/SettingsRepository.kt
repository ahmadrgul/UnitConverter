package com.example.unitconverter.domain.repository

import com.example.unitconverter.domain.model.settings.AppLanguage
import com.example.unitconverter.domain.model.settings.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<AppSettings>
    suspend fun setDarkMode(enabled: Boolean)
    suspend fun setSaveHistory(enabled: Boolean)
    suspend fun setLanguage(language: AppLanguage)
    suspend fun setPrecision(precision: Int)
}