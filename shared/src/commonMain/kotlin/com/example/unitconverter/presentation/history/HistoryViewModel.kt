package com.example.unitconverter.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverter.core.utils.ClipboardService
import com.example.unitconverter.core.utils.formatTimestamp
import com.example.unitconverter.core.utils.toCleanNumberString
import com.example.unitconverter.domain.model.quantity.QuantityRegistry
import com.example.unitconverter.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyRepository: HistoryRepository,
    private val clipBoardService: ClipboardService
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState(isLoading = true))
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    init {
        loadHistory()
    }

    fun toggleStarred(id: Long, isCurrStarred: Boolean){
        viewModelScope.launch {
            if (isCurrStarred) {
                historyRepository.updateIsStarred(id = id, isStarred = 0L)
            } else {
                historyRepository.updateIsStarred(id = id, isStarred = 1L)
            }
        }
    }

    fun setSelectedQuantity(quantityName: String) {
        _state.update {
            it.copy(selectedQuantityName = quantityName)
        }
    }

    fun copyHistoryItem(id: Long) {
        viewModelScope.launch {
            val historyItem = historyRepository.getHistoryById(id)
            if (historyItem != null) {
                val textToCopy = "${historyItem.fromValue} ${historyItem.fromUnit} = ${historyItem.toValue} ${historyItem.toUnit}"
                clipBoardService.copyToClipboard(textToCopy)
            }
        }
    }

    fun clearHistory(){
        viewModelScope.launch {
            historyRepository.clearHistory()
        }
    }

    fun deleteHistoryItem(id: Long) {
        viewModelScope.launch {
            historyRepository.deleteHistoryById(id)
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            combine(
                historyRepository.getHistory(),
                _state.map { it.selectedQuantityName }.distinctUntilChanged()
            ) { entities, selectedQuantityName ->

                val allItems = entities.map { entity ->
                    val quantity = QuantityRegistry.getQuantityById(entity.quantityId)
                    val fromUnit = quantity?.availableUnits?.find { it.unitName == entity.fromUnit }
                    val toUnit = quantity?.availableUnits?.find { it.unitName == entity.toUnit }

                    HistoryItemUI(
                        dbId = entity.id,
                        quantityId = entity.quantityId,
                        quantityName = "${quantity?.quantityName}",
                        fromUnit = "${fromUnit?.unitName} (${fromUnit?.symbol})",
                        toUnit = "${toUnit?.unitName} (${toUnit?.symbol})",
                        fromValue = entity.fromValue.toCleanNumberString(),
                        toValue = entity.toValue.toCleanNumberString(),
                        isStarred = entity.isStarred == 1L,
                        timestamp = formatTimestamp(entity.timestamp)
                    )
                }
                val quantitiesSet = allItems.map { it.quantityName }.toSet()

                if (selectedQuantityName == "") {
                    Pair(quantitiesSet, allItems.groupBy { it.timestamp.day })
                } else {
                    val filteredItems = allItems.filter { it.quantityName == selectedQuantityName }
                    Pair(quantitiesSet, filteredItems.groupBy { it.timestamp.day })
                }

            }.collect { (quantitiesSet, groupedItems) ->
                _state.update {
                    it.copy(
                        historyItems = groupedItems,
                        isLoading = false,
                        quantities = quantitiesSet
                    )
                }
            }
        }
    }
}