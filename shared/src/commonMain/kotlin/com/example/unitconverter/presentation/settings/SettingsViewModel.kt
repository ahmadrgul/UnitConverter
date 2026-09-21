package com.example.unitconverter.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverter.domain.model.settings.AppLanguage
import com.example.unitconverter.domain.repository.SettingsRepository
import com.example.unitconverter.presentation.settings.model.SectionId
import com.example.unitconverter.presentation.settings.model.SettingId
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
): ViewModel() {
    val settings: StateFlow<SettingsState> = settingsRepository.settings
        .map { SettingsState(it.toSections()) }
        .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = SettingsState())

    fun onToggleChange(id: SettingId, enabled: Boolean){
        viewModelScope.launch {
            when (id) {
                SettingId.THEME -> settingsRepository.setDarkMode(enabled)
                SettingId.HISTORY -> settingsRepository.setSaveHistory(enabled)
                else -> Unit
            }
        }
    }

    fun onChoiceSelect(id: SettingId, selected: String) {
        viewModelScope.launch {
            when (id) {
                SettingId.LANGUAGE -> settingsRepository.setLanguage(AppLanguage.fromTag(selected) ?: AppLanguage.ENGLISH)
                SettingId.PRECISION -> { settingsRepository.setPrecision(selected.toInt()) }
                else -> Unit
            }
        }
    }
}