package com.example.unitconverter.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverter.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    settingsRepository: SettingsRepository
) : ViewModel() {
    val isDarkTheme: StateFlow<Boolean> = settingsRepository.settings
        .map { it.darkMode }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )
}