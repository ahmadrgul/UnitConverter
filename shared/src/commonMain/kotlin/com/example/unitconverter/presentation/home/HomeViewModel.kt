package com.example.unitconverter.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverter.domain.model.quantity.QuantityCategory
import com.example.unitconverter.domain.model.quantity.QuantityRegistry
import com.example.unitconverter.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val isDarkTheme: StateFlow<Boolean> = settingsRepository.settings
        .map { it.darkMode }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    private val _state = MutableStateFlow(
        HomeState(
            categories = QuantityCategory.entries,
            groupedQuantities = QuantityRegistry.groupedQuantities
        )
    )
    val state = _state.asStateFlow()

    fun onSearchQueryChange(searchQuery: String) {
        _state.update { it.copy(searchQuery = searchQuery) }
        loadQuantities()
    }

    private fun loadQuantities() {
        val groupedQuantities = QuantityRegistry.groupedQuantities
        val searchQuery = _state.value.searchQuery

        if (searchQuery.isBlank()) {
            _state.update { it.copy(groupedQuantities = groupedQuantities) }
            return
        }

        val filteredQuantities = groupedQuantities.mapValues { (_, quantities) ->
            quantities.filter { it.quantityName.contains(searchQuery, ignoreCase = true) }
        }.filterValues { it.isNotEmpty() }

        _state.update { it.copy(groupedQuantities = filteredQuantities) }
    }
}