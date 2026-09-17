package com.example.unitconverter.presentation.history.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
    onCopyHistoryItem: (Long) -> Unit
){
    LazyColumn {
        items.keys.forEach { day ->
            item { Text(
                text = day,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            ) }

            itemsIndexed(items[day] ?: emptyList()) { index, item ->
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
                    onDelete = { onDeleteItem(item.dbId) },
                    onStarred = { onToggleStarred(item.dbId, item.isStarred) },
                    onCopy = { onCopyHistoryItem(item.dbId) }
                )
            }
        }
    }
}