package com.example.unitconverter.presentation.converter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.domain.model.Quantity
import com.example.unitconverter.domain.model.unit.QuantityUnit
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.check_icon
import com.example.unitconverter.generated.resources.x_icon
import com.example.unitconverter.presentation.theme.PaletteColors
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitsBottomSheet(
    isVisible: Boolean,
    quantity: Quantity<*>,
    selectedUnit: QuantityUnit,
    setSelectedUnit: (QuantityUnit) -> Unit,
    hideSheet: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = hideSheet,
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BottomSheetTopBar(
                    title = "Select ${quantity.quantityName} Unit",
                    onHide = {
                        scope.launch {
                            sheetState.hide()
                            hideSheet()
                        }
                    }
                )

                PopularUnitsSection(
                    popularUnits = quantity.popularUnits,
                    selectedUnit = selectedUnit,
                    onUnitSelect = {
                        setSelectedUnit(it)
                        scope.launch {
                            sheetState.hide()
                            hideSheet()
                        }
                    }
                )

                AllUnitsListSection(
                    units = quantity.availableUnits,
                    baseUnit = quantity.baseUnit,
                    selectedUnit = selectedUnit,
                    onUnitSelect = {
                        if (selectedUnit != it) {
                            setSelectedUnit(it)
                            scope.launch {
                                sheetState.hide()
                                hideSheet()
                            }
                        }
                    }
                )

            }
        }
    }
}

@Composable
fun BottomSheetTopBar(
    title: String,
    onHide: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        IconButton(
            onClick = onHide,
            modifier = Modifier.size(16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.x_icon),
                contentDescription = "X icon",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun PopularUnitsSection(
    onUnitSelect: (QuantityUnit) -> Unit,
    popularUnits: List<QuantityUnit>,
    selectedUnit: QuantityUnit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Popular Units",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            popularUnits.forEach { unit ->
                PopularUnitCard(
                    unit = unit,
                    isSelected = unit == selectedUnit,
                    onSelect = { onUnitSelect(unit) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun PopularUnitCard(
    unit: QuantityUnit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onSelect: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .height(70.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else Color.Gray.copy(
                    alpha = 0.1f
                ),
                shape = RoundedCornerShape(14.dp)
            )
            .background(
                shape = RoundedCornerShape(14.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.05f) else Color.LightGray.copy(
                    alpha = 0.1f
                )
            )
            .padding(8.dp)
            .clickable(
                onClick = {
                    if (!isSelected) {
                        onSelect()
                    }
                },
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            CheckBox(
                isChecked = true,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

        Column {
            Text(
                text = unit.symbol,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.DarkGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = unit.unitName.lowercase(),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.DarkGray,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AllUnitsListSection(
    units: List<QuantityUnit>,
    baseUnit: QuantityUnit,
    selectedUnit: QuantityUnit,
    onUnitSelect: (QuantityUnit) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "All Units",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Column {
            units.forEachIndexed { index, unit ->
                UnitListItem(
                    index = index,
                    unit = unit,
                    isBaseUnit = unit == baseUnit,
                    baseUnitSymbol = baseUnit.symbol,
                    isSelected = selectedUnit == unit,
                    onSelect = onUnitSelect,
                )

                if (index < units.size - 1) {
                    HorizontalDivider(
                        thickness = 0.2.dp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}

@Composable
fun UnitListItem(
    index: Int,
    unit: QuantityUnit,
    isBaseUnit: Boolean,
    baseUnitSymbol: String,
    isSelected: Boolean,
    onSelect: (QuantityUnit) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(
                onClick = { onSelect(unit) },
                interactionSource = interactionSource,
                indication = null
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        PaletteColors[index % PaletteColors.size].copy(alpha = 0.1f)
                            .copy(alpha = 0.05f)
                    )
                    .width(32.dp)
                    .height(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = unit.symbol,
                    color = PaletteColors[index % PaletteColors.size],
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            Text(
                text = unit.unitName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = if (isBaseUnit) "Base Unit" else "1 ${unit.symbol} = ${round(unit.baseMultiplier * 100) / 100.0} $baseUnitSymbol",
                color = Color.LightGray,
                fontSize = 13.sp
            )

            CheckBox(
                isChecked = isSelected,
                onClick = { onSelect(unit) }
            )

        }
    }
}

@Composable
fun CheckBox(
    isChecked: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(18.dp)
            .background(
                shape = CircleShape,
                color = if (isChecked) MaterialTheme.colorScheme.primary else Color.Transparent
            )
            .border(
                shape = CircleShape,
                color = if (isChecked) MaterialTheme.colorScheme.primary else Color.LightGray,
                width = 1.dp
            )
            .clip(CircleShape)
            .padding(2.dp)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        if (isChecked) {
            Icon(
                painter = painterResource(Res.drawable.check_icon),
                contentDescription = "Check Icon",
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}