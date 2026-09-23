package com.example.unitconverter.presentation.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unitconverter.presentation.history.components.ClearHistoryDialog
import com.example.unitconverter.presentation.history.components.HistoryItemCard
import com.example.unitconverter.presentation.history.components.HistoryScreenTopBar
import com.example.unitconverter.presentation.theme.getQuantityColor
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.emtpy_clipboard
import com.example.unitconverter.presentation.history.components.EmptyHistoryBanner
import com.example.unitconverter.presentation.history.components.HistoryItemsList
import com.example.unitconverter.presentation.history.components.QuantitiesFilterBar
import org.jetbrains.compose.resources.painterResource

@Composable
fun HistoryRoute(
    onNavigateToConverter: (String, Long) -> Unit
) {
    val viewModel: HistoryViewModel = koinViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()

    HistoryScreen(
        state = state,
        onClearHistory = { viewModel.clearHistory() },
        onDeleteItem = { viewModel.deleteHistoryItem(it) },
        onToggleStarred = { id, isStarred -> viewModel.toggleStarred(id, isStarred) },
        onSelectQuantity = { viewModel.setSelectedQuantity(it) },
        onCopyHistoryItem = { viewModel.copyHistoryItem(it) },
        onNavigateToConverter = onNavigateToConverter
    )
}

@Composable
fun HistoryScreen(
    state: HistoryState,
    onClearHistory: () -> Unit,
    onDeleteItem: (Long) -> Unit,
    onToggleStarred: (Long, Boolean) -> Unit,
    onSelectQuantity: (String) -> Unit,
    onCopyHistoryItem: (Long) -> Unit,
    onNavigateToConverter: (String, Long) -> Unit
) {
    var isClearDialogVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { HistoryScreenTopBar(
            onClickTrash = { isClearDialogVisible = true },
            isTrashEnabled = state.historyItems.isNotEmpty()
        ) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            if (state.isLoading) {}
            else if (state.historyItems.isEmpty()) EmptyHistoryBanner(modifier = Modifier.align(Alignment.Center))
            else {
                Column {
                    QuantitiesFilterBar(
                        quantities = state.quantities,
                        selectedQuantityName = state.selectedQuantityName,
                        onSelectQuantity = onSelectQuantity
                    )

                    HistoryItemsList(
                        items = state.historyItems,
                        onDeleteItem = onDeleteItem,
                        onToggleStarred = onToggleStarred,
                        onCopyHistoryItem = onCopyHistoryItem,
                        onNavigateToConverter = onNavigateToConverter
                    )
                }
            }
        }

        if (isClearDialogVisible) {
            ClearHistoryDialog(
                onClearHistory = onClearHistory,
                onDismiss = { isClearDialogVisible = false }
            )
        }

    }
}