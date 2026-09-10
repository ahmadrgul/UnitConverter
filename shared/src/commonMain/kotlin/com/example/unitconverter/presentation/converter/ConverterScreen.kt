package com.example.unitconverter.presentation.converter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unitconverter.domain.model.unit.QuantityUnit
import com.example.unitconverter.presentation.converter.components.ConversionCard
import com.example.unitconverter.presentation.converter.components.ConverterTopBar
import com.example.unitconverter.presentation.converter.components.CopyResultButton
import com.example.unitconverter.presentation.converter.components.UnitsBottomSheet
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ConverterRoute(
    quantityId: String,
    onNavigateBack: () -> Unit
) {
    val viewModel: ConverterViewModel = koinViewModel(parameters = { parametersOf(quantityId) })

    val state by viewModel.state.collectAsStateWithLifecycle()

    ConverterScreen(
        state = state,
        onInputValueChanged = { viewModel.onInputValueChange(it) },
        onFromUnitChange = { viewModel.onFromUnitChange(it) },
        onToUnitChange = { viewModel.onToUnitChange(it) },
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun ConverterScreen(
    state: ConverterState,
    onInputValueChanged: (String) -> Unit,
    onFromUnitChange: (QuantityUnit) -> Unit,
    onToUnitChange: (QuantityUnit) -> Unit,
    onNavigateBack: () -> Unit,
) {
    var showInputUnitSheet by remember { mutableStateOf(false) }
    var showOutputUnitSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ConverterTopBar(
                title = state.currentQuantity.quantityName,
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            ConversionCard(
                label = "From",
                value = state.inputValue,
                approximateValue = state.approximateInputValue,
                editable = true,
                unit = state.selectedFromUnit,
                onUnitClick = { showInputUnitSheet = true },
                onValueChange = onInputValueChanged,
            ) { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (state.inputValue.isEmpty()) {
                        Text(
                            text = "Enter value",
                            color = Color.LightGray,
                            maxLines = 1,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                    innerTextField()
                }
            }

            ConversionCard(
                label = "To",
                value = state.convertedValue,
                approximateValue = state.approximateConvertedValue,
                editable = false,
                unit = state.selectedToUnit,
                onUnitClick = { showOutputUnitSheet = true },
                onValueChange = {},
            ) { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (state.inputValue.isEmpty()) {
                        Text(
                            text = "–",
                            color = Color.LightGray,
                            maxLines = 1,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    innerTextField()
                }
            }

            CopyResultButton()
        }

        UnitsBottomSheet(
            isVisible = showInputUnitSheet,
            quantity = state.currentQuantity,
            selectedUnit = state.selectedFromUnit,
            setSelectedUnit = { onFromUnitChange(it) },
            hideSheet = { showInputUnitSheet = false }
        )

        UnitsBottomSheet(
            isVisible = showOutputUnitSheet,
            quantity = state.currentQuantity,
            selectedUnit = state.selectedToUnit,
            setSelectedUnit = { onToUnitChange(it) },
            hideSheet = { showOutputUnitSheet = false }
        )
    }
}
