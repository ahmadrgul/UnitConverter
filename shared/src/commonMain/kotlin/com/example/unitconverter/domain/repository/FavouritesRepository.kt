package com.example.unitconverter.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    fun getFavouriteQuantityIds(): Flow<Set<String>>
    suspend fun toggleFavourite(quantityId: String)
}