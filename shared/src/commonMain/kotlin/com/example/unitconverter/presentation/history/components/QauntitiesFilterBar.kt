package com.example.unitconverter.presentation.history.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuantitiesFilterBar(
    quantities: Set<String>,
    selectedQuantityName: String,
    onSelectQuantity: (String) -> Unit,
){
    val scrollState = rememberScrollState()
    val options = remember(quantities) { listOf("" to "All") + quantities.map { it to it } }

    Row(
        modifier = Modifier.horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { (value, label) ->
            val isSelected = value == selectedQuantityName

            FilterChip(
                selected = isSelected,
                onClick = { onSelectQuantity(value) },
                label = { Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp) },
                shape = CircleShape,
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.surface,
                    labelColor = Color.Gray
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = isSelected,
                    borderColor = Color.LightGray,
                    selectedBorderColor = MaterialTheme.colorScheme.primary,
                    borderWidth = 0.05.dp,
                    selectedBorderWidth = 0.05.dp
                ),
                contentPadding = PaddingValues(vertical = 6.dp, horizontal = 12.dp)
            )
        }
    }
}