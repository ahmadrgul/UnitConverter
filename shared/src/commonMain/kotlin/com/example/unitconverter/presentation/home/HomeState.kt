package com.example.unitconverter.presentation.home

import com.example.unitconverter.domain.model.quantity.Quantity
import com.example.unitconverter.domain.model.quantity.QuantityCategory
import com.example.unitconverter.domain.model.quantity.unit.QuantityUnit
import org.jetbrains.compose.resources.DrawableResource

data class SearchedUnitItem(
    val quantityId: String,
    val unitName: String,
    val unitSymbol: String,
    val quantityName: String,
)

data class HomeState(
    val searchQuery: String = "",
    val categories: List<QuantityCategory> = emptyList(),
    val allUnits: List<SearchedUnitItem> = emptyList(),
    val searchedUnits: Map<String, List<SearchedUnitItem>> = emptyMap(),
    val groupedQuantities: Map<QuantityCategory, List<Quantity<QuantityUnit>>> = emptyMap()
)