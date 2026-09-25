package com.example.unitconverter.presentation.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverter.core.utils.ClipboardService
import com.example.unitconverter.core.utils.toCleanNumberString
import com.example.unitconverter.domain.model.quantity.QuantityRegistry
import com.example.unitconverter.domain.model.quantity.unit.QuantityUnit
import com.example.unitconverter.domain.repository.FavouritesRepository
import com.example.unitconverter.domain.repository.HistoryRepository
import com.example.unitconverter.domain.repository.SettingsRepository
import com.example.unitconverter.domain.usecase.ConvertUnitUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.round
import kotlin.time.Clock

enum class KeyboardKey(val symbol: String) {
    NUM_0("0"), NUM_1("1"), NUM_2("2"),
    NUM_3("3"), NUM_4("4"), NUM_5("5"),
    NUM_6("6"), NUM_7("7"), NUM_8("8"),
    NUM_9("9"),
    BACKSPACE("⌫"),
    DOT("."),
    AC("AC"),
    EQUAL("="),
}

class ConverterViewModel(
    private val quantityId: String,
    private val fromUnitName: String?,
    private val historyId: Long?,
    private val convertUnit: ConvertUnitUseCase,
    private val clipboardService: ClipboardService,
    private val favouritesRepository: FavouritesRepository,
    private val historyRepository: HistoryRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val quantity = QuantityRegistry.getQuantityById(quantityId)
        ?: throw IllegalArgumentException("Unknown quantity ID: '$quantityId'")

    private val historyEnabled = settingsRepository.settings
        .map { it.saveHistory }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    private val precision = settingsRepository.settings
        .map { it.decimalPrecision }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = 2
        )

    private val _state = MutableStateFlow(
        ConverterState(
            currentQuantity = quantity,
            selectedFromUnit = quantity.defaultFrom,
            selectedToUnit = quantity.defaultTo
        )
    )

    val state: StateFlow<ConverterState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            if (fromUnitName != null) {
                val unit = QuantityRegistry.getUnitByName(fromUnitName)
                if (unit != null) {
                    _state.update { it.copy(
                        selectedFromUnit = unit,
                    ) }
                }
            }

            if (historyId != null) {
                val historyItem = historyRepository.getHistoryById(historyId)
                if (historyItem != null) {
                    _state.update { it.copy(
                        inputValue = historyItem.fromValue.toString(),
                        approximateInputValue = (round(historyItem.fromValue * 100) / 100.0).toString(),
                        selectedFromUnit = QuantityRegistry.getUnitByName(historyItem.fromUnit)!!,
                        convertedValue = historyItem.toValue.toString(),
                        approximateConvertedValue = (round(historyItem.toValue * 100) / 100.0).toString(),
                        selectedToUnit = QuantityRegistry.getUnitByName(historyItem.toUnit)!!,
                    ) }
                }
            }

            favouritesRepository.getFavouriteQuantityIds().collect { favouritesSet ->
                _state.update {
                    it.copy(isFavourite = favouritesSet.contains(quantityId))
                }
            }
        }
    }

    fun onFromUnitChange(newFromUnit: QuantityUnit) {
        _state.update { it.copy(selectedFromUnit = newFromUnit) }
        performConversionFromCurrInput()
    }

    fun onToUnitChange(newToUnit: QuantityUnit) {
        _state.update { it.copy(selectedToUnit = newToUnit) }
        performConversionFromCurrInput()
    }

    fun onUnitsSwap() {
        val prevToUnit = _state.value.selectedToUnit
        val prevToValue = _state.value.convertedValue

        _state.update { it.copy(selectedToUnit = _state.value.selectedFromUnit) }
        _state.update { it.copy(selectedFromUnit = prevToUnit) }

        _state.update { it.copy(convertedValue = _state.value.inputValue) }
        _state.update { it.copy(inputValue = prevToValue) }

        performConversionFromCurrInput()
    }

    fun onCopyResults() {
        clipboardService.copyToClipboard("${_state.value.convertedValue} ${_state.value.selectedToUnit.symbol}")
    }

    fun onToggleFavourite() {
        viewModelScope.launch {
            favouritesRepository.toggleFavourite(quantity.id)
        }
    }

    private fun saveCurrToHistory() {
        if (historyEnabled.value) {
            viewModelScope.launch {
                historyRepository.insertHistory(
                    quantity.id,
                    _state.value.selectedFromUnit.unitName,
                    _state.value.selectedToUnit.unitName,
                    _state.value.inputValue.toDouble(),
                    _state.value.convertedValue.toDouble(),
                    Clock.System.now().toEpochMilliseconds(),
                )
            }
        }
    }

    private fun performConversionFromCurrInput() {
        val currentState = _state.value
        val textInput = currentState.inputValue

        if (textInput.isBlank()) {
            _state.update {
                it.copy(
                    convertedValue = "",
                    approximateConvertedValue = "",
                    approximateInputValue = ""
                )
            }
            return
        }

        val rawDoubleInput = textInput.toDoubleOrNull() ?: 0.0
        val rawDoubleOutput =
            convertUnit(rawDoubleInput, currentState.selectedFromUnit, currentState.selectedToUnit)

        val precisionCalculationNumber = (10.0).pow(precision.value)

        val boundedOutput = round(rawDoubleOutput * precisionCalculationNumber) / precisionCalculationNumber

        val roundedInput = round(rawDoubleInput * 100) / 100.0
        val roundedOutput = round(boundedOutput * 100) / 100.0

        _state.update {
            it.copy(
                convertedValue = boundedOutput.toCleanNumberString(),
                approximateInputValue = roundedInput.toString(),
                approximateConvertedValue = roundedOutput.toString()
            )
        }
    }

    private fun removeCharacterAtCurrCursor() {
        val currInputText = _state.value.inputValue
        val currCursorIndex = _state.value.inputCursorIndex

        if (currCursorIndex <= 0) {
            return
        }

        val beforeCursorText = currInputText.substring(0, currCursorIndex)
        val afterCursorText = currInputText.substring(currCursorIndex)

        _state.update { it.copy(
            inputValue = beforeCursorText.dropLast(1) + afterCursorText,
            inputCursorIndex = currCursorIndex - 1
        ) }
    }

    private fun clearAll() {
        _state.update {
            it.copy(
                inputValue = "",
                convertedValue = "",
                approximateInputValue = "",
                approximateConvertedValue = "",
                inputCursorIndex = 0
            )
        }
    }

    private fun insertPointAtCurrCursor() {
        if (_state.value.inputValue.contains(".")) return

        if (_state.value.inputValue.isEmpty()) {
            _state.update { it.copy(
                inputValue = _state.value.inputValue.plus("0."),
                inputCursorIndex = 2
            ) }
            return
        }

        val currInputText = _state.value.inputValue
        val currCursorIndex = _state.value.inputCursorIndex

        val beforeCursorText = currInputText.substring(0, currCursorIndex)
        val afterCursorText = currInputText.substring(currCursorIndex)

        _state.update { it.copy(
            inputValue = "$beforeCursorText.$afterCursorText",
            inputCursorIndex = currCursorIndex + 1
        ) }
    }

    private fun insertNumberAtCurrCursor(key: KeyboardKey) {
        if (key == KeyboardKey.NUM_0 && _state.value.inputValue == "0") return

        val currInputText = _state.value.inputValue
        val currCursorIndex = _state.value.inputCursorIndex

        val beforeCursorText = currInputText.substring(0, currCursorIndex)
        val afterCursorText = currInputText.substring(currCursorIndex)

        _state.update { it.copy(
            inputValue = beforeCursorText + key.symbol + afterCursorText,
            inputCursorIndex = currCursorIndex + 1
        ) }
    }

    fun onCursorMoved(newIndex: Int) {
        _state.update { it.copy(inputCursorIndex = newIndex) }
    }

    fun onKeyPressed(key: KeyboardKey) {
        when (key) {
            KeyboardKey.BACKSPACE -> removeCharacterAtCurrCursor()
            KeyboardKey.AC -> clearAll()
            KeyboardKey.DOT -> insertPointAtCurrCursor()
            KeyboardKey.EQUAL -> {
                performConversionFromCurrInput()
                saveCurrToHistory()
            }
            else ->  insertNumberAtCurrCursor(key)
        }
    }

    fun setInputValue(value: String) {
        _state.update { it.copy(
            inputValue = value,
        ) }
    }
}