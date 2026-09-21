package com.example.unitconverter.presentation.home

import com.example.unitconverter.domain.model.quantity.Quantity
import com.example.unitconverter.domain.model.quantity.QuantityCategory
import com.example.unitconverter.domain.model.quantity.unit.QuantityUnit

data class HomeState(
    val searchQuery: String = "",
    val categories: List<QuantityCategory> = emptyList(),
    val groupedQuantities: Map<QuantityCategory, List<Quantity<QuantityUnit>>> = emptyMap()
)