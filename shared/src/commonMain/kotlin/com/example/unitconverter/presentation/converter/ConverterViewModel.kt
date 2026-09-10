package com.example.unitconverter.presentation.converter

import androidx.lifecycle.ViewModel
import com.example.unitconverter.core.utils.ClipboardService
import com.example.unitconverter.domain.model.QuantityRegistry
import com.example.unitconverter.domain.model.unit.QuantityUnit
import com.example.unitconverter.domain.usecase.ConvertUnitUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.selects.select
import kotlin.math.round

class ConverterViewModel(
    quantityId: String,
    private val convertUnit: ConvertUnitUseCase,
    private val clipboardService: ClipboardService
) : ViewModel() {
    private val quantity = QuantityRegistry.getQuantityById(quantityId)
        ?: throw IllegalArgumentException("Unknown quantity ID: '$quantityId'")

    private val _state = MutableStateFlow(
        ConverterState(
            currentQuantity = quantity,
            selectedFromUnit = quantity.defaultFrom,
            selectedToUnit = quantity.defaultTo
        )
    )

    val state: StateFlow<ConverterState> = _state.asStateFlow()

    fun onInputValueChange(newValue: String) {
        _state.update { it.copy(inputValue = newValue) }
        updateState()
    }

    fun onFromUnitChange(newFromUnit: QuantityUnit) {
        _state.update { it.copy(selectedFromUnit = newFromUnit) }
        updateState()
    }

    fun onToUnitChange(newToUnit: QuantityUnit) {
        _state.update { it.copy(selectedToUnit = newToUnit) }
        updateState()
    }

    fun onUnitsSwap() {
        val prevToUnit = _state.value.selectedToUnit
        val prevToValue = _state.value.convertedValue

        _state.update { it.copy(selectedToUnit = _state.value.selectedFromUnit) }
        _state.update { it.copy(selectedFromUnit = prevToUnit)}

        _state.update { it.copy(convertedValue = _state.value.inputValue)}
        _state.update { it.copy(inputValue = prevToValue) }

        updateState()
    }

    fun onCopyResults() {
        clipboardService.copyToClipboard("${_state.value.convertedValue} ${_state.value.selectedToUnit.symbol}")
    }

    private fun updateState() {
        val currentState = _state.value
        val textInput = currentState.inputValue

        if (textInput.isBlank()) {
            _state.update { it.copy(
                convertedValue = "",
                approximateConvertedValue = "",
                approximateInputValue = ""
            ) }
            return
        }

        val numInput = textInput.toDoubleOrNull() ?: 0.0
        val numOutput =
            convertUnit(numInput, currentState.selectedFromUnit, currentState.selectedToUnit)

        val roundedInput = round(numInput * 100) / 100.0
        val roundedOutput = round(numOutput * 100) / 100.0

        _state.update {
            it.copy(
                convertedValue = numOutput.toString(),
                approximateInputValue = roundedInput.toString(),
                approximateConvertedValue = roundedOutput.toString()
            )
        }
    }
}