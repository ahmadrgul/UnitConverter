package com.example.unitconverter.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unitconverter.domain.Measurement
import com.example.unitconverter.domain.MeasurementRegistry
import com.example.unitconverter.domain.MeasurementUnit
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.check_icon
import com.example.unitconverter.generated.resources.chevron_down_icon
import com.example.unitconverter.generated.resources.chevron_left_icon
import com.example.unitconverter.generated.resources.copy_icon
import com.example.unitconverter.generated.resources.heart_icon
import com.example.unitconverter.generated.resources.x_icon
import com.example.unitconverter.theme.PaletteColors
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun CalculatorScreen(
    navController: NavController,
    routeId: String
) {
    val measurement: Measurement<MeasurementUnit> = MeasurementRegistry.getById(routeId) ?: return

    var fromUnit by remember { mutableStateOf(measurement.units[0]) }
    var toUnit by remember { mutableStateOf(measurement.units[1]) }

    var showInputUnitSheet by remember { mutableStateOf(false) }
    var showOutputUnitSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CalculatorTopBar(
               label = measurement.label,
               navigateBack = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            var inputState by remember { mutableStateOf("") }
            var outputState by remember { mutableStateOf("") }

            CalculationCard(
                label = "From",
                unit = fromUnit,
                onUnitClick = { showInputUnitSheet = true }
            ) {
                BasicTextField(
                    enabled = true,
                    value = inputState,
                    onValueChange = { newValue ->
                        inputState = newValue
                        val inputDouble = inputState.toDoubleOrNull() ?: 0.0
                        val outputDouble = measurement.convert(inputDouble, fromUnit, toUnit)
                        outputState = outputDouble.toString()
                    },
                    textStyle = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (inputState.isEmpty()) {
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
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            CalculationCard(
                label = "To",
                unit = toUnit,
                onUnitClick = { showOutputUnitSheet = true }
            ) {
                BasicTextField(
                    enabled = false,
                    value = outputState,
                    onValueChange = {},
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (outputState.isEmpty()) {
                                Text(
                                    text = "–",
                                    color = Color.DarkGray,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            CopyResultButton()
        }

        UnitsBottomSheet(
            measurementLabel = measurement.label,
            selectedUnit = fromUnit,
            setSelectedUnit = { fromUnit = it },
            showSheet = showInputUnitSheet,
            units = measurement.units,
            hideSheet = { showInputUnitSheet = false }
        )

        UnitsBottomSheet(
            measurementLabel = measurement.label,
            selectedUnit = toUnit,
            setSelectedUnit = { toUnit = it },
            showSheet = showOutputUnitSheet,
            units = measurement.units,
            hideSheet = { showOutputUnitSheet = false }
        )
    }
}

@Composable
fun CalculatorTopBar(
    label: String,
    navigateBack: () -> Unit,
){
    TopAppBar(
        title = { Text(
            text = label,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )},
        navigationIcon = { IconButton(
            onClick = navigateBack,
            modifier = Modifier.size(32.dp)
        ) {
                Icon(
                    painter = painterResource(Res.drawable.chevron_left_icon),
                    contentDescription = "Chevron Left",
                    modifier = Modifier.size(32.dp)
                )}
        },
        actions = { IconButton(onClick = {}) {
            Icon(
                painter = painterResource(Res.drawable.heart_icon),
                contentDescription = "Heart",
                modifier = Modifier.size(28.dp)
            )
        }},
        colors =  TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun CalculationCard(
    label: String,
    unit: MeasurementUnit,
    onUnitClick: () -> Unit,
    textField: @Composable () -> Unit,
){
    val cardShape = RoundedCornerShape(24.dp)

    Column (
        modifier = Modifier
            .dropShadow(
                shadow = Shadow(
                    color = Color.LightGray.copy(alpha = 0.4f),
                    radius = 6.dp,
                    spread = 2.dp,
                    offset = DpOffset(0.dp, 0.dp)
                ),
                shape = cardShape
            )
            .background(
                shape = cardShape,
                color = MaterialTheme.colorScheme.surface
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            textField()

            Row (
                modifier = Modifier.clickable(onClick = onUnitClick),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "${unit.label} (${unit.symbol})",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                )
                Icon(
                    painter = painterResource(Res.drawable.chevron_down_icon),
                    contentDescription = "Chevron Down",
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "≈ 0",
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun CopyResultButton(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .clickable(onClick = {
            })
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.copy_icon),
            contentDescription = "Copy",
            tint = Color.White
        )
        Text(
            text = "Copy Result",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitsBottomSheet(
    measurementLabel: String,
    selectedUnit: MeasurementUnit,
    setSelectedUnit: (MeasurementUnit) -> Unit,
    showSheet: Boolean,
    hideSheet: () -> Unit,
    units: List<MeasurementUnit>
){
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = hideSheet,
            sheetState = sheetState
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Select $measurementLabel Unit",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    IconButton(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                hideSheet()
                            }
                        },
                        modifier = Modifier.size(16.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.x_icon),
                            contentDescription = "X icon",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Popular Units",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    units.shuffled().take(4).forEach { unit ->
                        val isChecked = (unit == selectedUnit)

                        Box (
                            modifier = Modifier
                                .height(70.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (isChecked) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else Color.Gray.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .background(
                                    shape = RoundedCornerShape(14.dp),
                                    color = if (isChecked) MaterialTheme.colorScheme.primary.copy(alpha =0.05f) else Color.LightGray.copy(alpha = 0.1f)
                                )
                                .padding(8.dp)
                                .weight(1f)
                                .clickable(
                                    onClick = {
                                        if (!isChecked) {
                                            setSelectedUnit(unit)
                                            scope.launch {
                                                sheetState.hide()
                                                hideSheet()
                                            }
                                        }
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isChecked) {
                                Box (
                                    modifier = Modifier
                                        .size(18.dp)
                                        .background(
                                            shape = CircleShape,
                                            color = if (isChecked) MaterialTheme.colorScheme.primary else Color.Transparent
                                        )
                                        .border(
                                            shape = CircleShape,
                                            color = if(isChecked) MaterialTheme.colorScheme.primary else Color.LightGray,
                                            width = 1.dp
                                        )
                                        .clip(CircleShape)
                                        .padding(2.dp)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.check_icon),
                                        contentDescription = "Check Icon",
                                        tint = MaterialTheme.colorScheme.surface
                                    )
                                }
                            }

                            Column {
                                Text(
                                    text = unit.symbol,
                                    color = if (isChecked) MaterialTheme.colorScheme.primary else Color.DarkGray,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = unit.label.lowercase(),
                                    color = if (isChecked) MaterialTheme.colorScheme.primary else Color.DarkGray,
                                    fontSize = 12.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "All Units",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                units.forEachIndexed { index, unit ->
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(PaletteColors[index % PaletteColors.size].copy(alpha = 0.1f).copy(alpha = 0.05f))
                                    .width(38.dp)
                                    .height(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text (
                                    text = unit.symbol,
                                    color = PaletteColors[index % PaletteColors.size],
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                            Text(
                                text = unit.label,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                        }

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Text(
                                text = "1 ${unit.symbol} = ${unit.factorToBase} ${units[0].symbol}",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                            val isChecked: Boolean = (unit == selectedUnit)

                            Box (
                                modifier = Modifier
                                    .size(18.dp)
                                    .background(
                                        shape = CircleShape,
                                        color = if (isChecked) MaterialTheme.colorScheme.primary else Color.Transparent
                                    )
                                    .border(
                                        shape = CircleShape,
                                        color = if(isChecked) MaterialTheme.colorScheme.primary else Color.LightGray,
                                        width = 1.dp
                                    )
                                    .clip(CircleShape)
                                    .clickable(
                                        onClick = {
                                            if (!isChecked) {
                                                setSelectedUnit(unit)
                                                scope.launch {
                                                    sheetState.hide()
                                                    hideSheet()
                                                }
                                            }
                                        }
                                    )
                                    .padding(2.dp)
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
                    }
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
}