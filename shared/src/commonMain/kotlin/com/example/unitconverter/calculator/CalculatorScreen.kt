package com.example.unitconverter.calculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.navigation.Measurement

@Composable
fun CalculatorScreen(measurement: Measurement) {
    Text(
        text = "So, you wanna calculate $measurement",
        modifier = Modifier.padding(top = 86.dp),
        fontSize = 24.sp
    )
}