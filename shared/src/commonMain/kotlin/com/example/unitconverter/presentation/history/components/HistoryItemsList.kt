package com.example.unitconverter.presentation.history.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverter.presentation.history.HistoryItemUI
import com.example.unitconverter.presentation.theme.getQuantityColor

@Composable
fun HistoryItemsList(
    items: Map<String, List<HistoryItemUI>>,
    onDeleteItem: (Long) -> Unit,
    onToggleStarred: (Long, Boolean) -> Unit,
    onCopyHistoryItem: (Long) -> Unit,
    onNavigateToConverter: (String, Long) -> Unit,
){
    LazyColumn (
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items.keys.forEach { day ->
            item { Text(
                text = day,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            ) }

            itemsIndexed(
                items = items[day] ?: emptyList(),
                key = { idx, item -> item.dbId }
            ) { index, item ->
                HistoryItemCard(
                    quantityName = item.quantityName,
                    quantityId = item.quantityId,
                    fromUnit = item.fromUnit,
                    toUnit = item.toUnit,
                    inputValue = item.fromValue,
                    convertedValue = item.toValue,
                    color = getQuantityColor(index),
                    isStarred = item.isStarred,
                    timestamp = "${if (day != "Today") "${item.timestamp.day}," else ""} ${item.timestamp.time}",
                    modifier = Modifier.animateItem(),
                    onDelete = { onDeleteItem(item.dbId) },
                    onStarred = { onToggleStarred(item.dbId, item.isStarred) },
                    onCopy = { onCopyHistoryItem(item.dbId) },
                    onClick = { onNavigateToConverter(item.quantityId, item.dbId) }
                )
            }
        }
    }
}