package com.example.unitconverter.presentation.home

import com.example.unitconverter.domain.model.Quantity
import com.example.unitconverter.domain.model.QuantityCategory
import com.example.unitconverter.domain.model.unit.QuantityUnit

data class HomeState(
    val searchQuery: String = "",
    val categories: List<QuantityCategory> = emptyList(),
    val groupedQuantities: Map<QuantityCategory, List<Quantity<QuantityUnit>>> = emptyMap()
)