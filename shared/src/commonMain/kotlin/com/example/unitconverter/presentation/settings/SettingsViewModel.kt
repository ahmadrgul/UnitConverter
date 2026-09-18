package com.example.unitconverter.presentation.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.ViewModel
import com.example.unitconverter.domain.model.settings.SettingsRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel: ViewModel() {
    private val _state =
        MutableStateFlow(SettingsState(
            items = SettingsRegistry.getAllSettingsItems(),
            isDarkTheme = false,
            languages = listOf("English", "Urdu", "French"),
            selectedLanguage = "English",
            isHistoryEnabled = false,
            precisionValues = listOf(1, 2, 3),
            selectedPrecision = 2
        ))

    val state = _state.asStateFlow()

    fun toggleDarkTheme(){
        _state.update { it.copy(isDarkTheme = !_state.value.isDarkTheme) }
        TODO("Not implemented, yet")
    }

    fun setAppLanguage(lang: String){
        _state.update { it.copy(selectedLanguage = lang) }
        TODO("Not implemented, yet")
    }

    fun toggleHistory(){
        _state.update { it.copy(isHistoryEnabled = !_state.value.isHistoryEnabled) }
        TODO("Not implemented, yet")
    }

    fun setDecimalPrecision(precision: Int) {
        _state.update { it.copy(selectedPrecision = precision) }
        TODO("Not implemented, yet")
    }
}