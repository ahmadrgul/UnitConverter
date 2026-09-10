package com.example.unitconverter.domain.usecase

import com.example.unitconverter.domain.model.unit.LengthUnit
import kotlin.test.Test
import kotlin.test.assertEquals

class ConvertUnitUseCaseTest {
    private val convertUnit = ConvertUnitUseCase()

    @Test
    fun convert_meters_to_kilometers_return_correct_value() {
        val result = convertUnit(10900.0, LengthUnit.METER, LengthUnit.KILOMETER)
        assertEquals(10.9, result, 0.001)
    }
}