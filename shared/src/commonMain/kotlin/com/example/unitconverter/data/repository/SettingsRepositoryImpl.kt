package com.example.unitconverter.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.unitconverter.domain.model.settings.AppLanguage
import com.example.unitconverter.domain.model.settings.AppSettings
import com.example.unitconverter.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    private object Keys {
        val THEME = booleanPreferencesKey("dark_theme")
        val HISTORY = booleanPreferencesKey("save_history")
        val LANGUAGE = stringPreferencesKey("language")
        val PRECISION = intPreferencesKey("precision")
    }

    override val settings: Flow<AppSettings> = dataStore.data
        .map {
            AppSettings(
                darkMode = it[Keys.THEME] ?: false,
                saveHistory = it[Keys.HISTORY] ?: true,
                language = AppLanguage.fromTag(it[Keys.LANGUAGE]) ?: AppLanguage.ENGLISH,
                decimalPrecision = it[Keys.PRECISION] ?: 2
            )
        }
        .distinctUntilChanged()

    override suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { it[Keys.THEME] = enabled }
    }

    override suspend fun setSaveHistory(enabled: Boolean) {
        dataStore.edit { it[Keys.HISTORY] = enabled }
    }

    override suspend fun setLanguage(language: AppLanguage) {
        dataStore.edit { it[Keys.LANGUAGE] = language.tag }
    }

    override suspend fun setPrecision(precision: Int) {
        dataStore.edit { it[Keys.PRECISION] = precision }
    }
}
