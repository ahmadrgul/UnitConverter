package com.example.unitconverter.presentation.converter

import com.example.unitconverter.domain.model.Quantity
import com.example.unitconverter.domain.model.unit.QuantityUnit

data class ConverterState(
    val isFavourite: Boolean = false,
    val inputValue: String = "",
    val approximateInputValue: String = "",
    val currentQuantity: Quantity<QuantityUnit>,
    val selectedFromUnit: QuantityUnit,
    val selectedToUnit: QuantityUnit,
    val convertedValue: String = "",
    val approximateConvertedValue: String = ""
)