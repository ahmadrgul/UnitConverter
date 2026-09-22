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
        HomeState()
    )
    val state = _state.asStateFlow()

    init {
        val categories = QuantityCategory.entries
        val groupedQuantities = QuantityRegistry.groupedQuantities

        val allUnits = QuantityRegistry.allQuantities.flatMap { it.availableUnits.map { unit -> SearchedUnitItem(
            quantityId = it.id,
            quantityName = it.quantityName,
            unitName = unit.unitName
        )}}.sortedBy { it.unitName }

        _state.update { it.copy(
            categories = categories,
            allUnits = allUnits,
            searchedUnits = mapOf("All Units" to allUnits),
            groupedQuantities = groupedQuantities
        )}
    }

    fun onSearchQueryChange(searchQuery: String) {
        val cleanQuery = searchQuery.trim()

        if (cleanQuery == "") {
            _state.update { it.copy(
                searchQuery = searchQuery,
                searchedUnits = mapOf(
                    "All Units" to _state.value.allUnits
                )
            )}
            return
        }

        val searchedUnits = _state.value.allUnits.filter {
            it.quantityName.contains(cleanQuery, ignoreCase = true) ||
            it.unitName.contains(cleanQuery, ignoreCase = true)
        }

        val (best, others) = searchedUnits.partition {
            it.unitName.startsWith(cleanQuery, ignoreCase = true)
        }

        _state.update { it.copy(
            searchQuery = searchQuery,
            searchedUnits = mapOf(
                "Best Matches" to best,
                "Other Matches" to others
            ).filterValues { list -> list.isNotEmpty() }
        )}
    }
}