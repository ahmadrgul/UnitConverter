package com.example.unitconverter.domain.repository

import com.example.unitconverter.database.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getHistory(): Flow<List<HistoryEntity>>
    suspend fun insertHistory(
        quantityId: String,
        fromUnit: String,
        toUnit: String,
        fromValue: Double,
        toValue: Double,
        timestamp: Long
    )
    suspend fun clearHistory()
}