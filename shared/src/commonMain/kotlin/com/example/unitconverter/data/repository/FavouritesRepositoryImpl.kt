package com.example.unitconverter.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.unitconverter.domain.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class FavouritesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : FavouritesRepository {
    private val favKey = stringSetPreferencesKey("favourite_quantities")

    override fun getFavouriteQuantityIds(): Flow<Set<String>> {
        return dataStore.data.map { preferences ->
            preferences[favKey] ?: emptySet()
        }
    }

    override suspend fun toggleFavourite(quantityId: String) {
        dataStore.edit { preferences ->
            val currFavourites = preferences[favKey] ?: emptySet()
            if (currFavourites.contains(quantityId)) {
                preferences[favKey] = currFavourites - quantityId
            } else {
                preferences[favKey] = currFavourites + quantityId
            }
        }
    }
}