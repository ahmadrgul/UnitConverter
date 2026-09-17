package com.example.unitconverter.presentation.history

import com.example.unitconverter.core.utils.DayTime
import com.example.unitconverter.database.HistoryEntity

data class HistoryState(
    val historyItems: Map<String, List<HistoryItemUI>> = emptyMap(),
    val quantities: Set<String> = emptySet(),
    val isLoading: Boolean = true,
    val selectedQuantityName: String = ""
)

data class HistoryItemUI(
    val dbId: Long,
    val quantityId: String,
    val quantityName: String,
    val fromUnit: String,
    val toUnit: String,
    val fromValue: String,
    val toValue: String,
    val isStarred: Boolean,
    val timestamp: DayTime
)