package com.example.unitconverter.domain.usecase

import com.example.unitconverter.domain.model.unit.QuantityUnit

class ConvertUnitUseCase {
    operator fun <T : QuantityUnit> invoke(value: Double, from: T, to: T): Double {
        val valueInBase = value * from.baseMultiplier
        return valueInBase / to.baseMultiplier
    }
}