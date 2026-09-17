package com.example.unitconverter.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.unitconverter.database.HistoryEntity
import com.example.unitconverter.database.UnitConverterDatabase
import com.example.unitconverter.domain.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(
    private val database: UnitConverterDatabase
) : HistoryRepository {

    private val queries = database.historyEntityQueries

    override fun getHistory(): Flow<List<HistoryEntity>> {
        return queries.getAllHistory()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    override suspend fun getHistoryById(id: Long): HistoryEntity? {
       return queries
           .getHistoryById(id)
           .executeAsOneOrNull()
    }

    override suspend fun insertHistory(
        quantityId: String,
        fromUnit: String,
        toUnit: String,
        fromValue: Double,
        toValue: Double,
        timestamp: Long
    ) {
        queries.insertHistory(
            quantityId,
            fromUnit,
            toUnit,
            fromValue,
            toValue,
            timestamp
        )
    }

    override suspend fun clearHistory() {
        queries.clearHistory()
    }

    override suspend fun deleteHistoryById(id: Long) {
        queries.deleteById(id)
    }

    override suspend fun updateIsStarred(id: Long, isStarred: Long) {
        queries.updateIsStarred(id = id, isStarred = isStarred)
    }
}