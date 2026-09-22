package com.example.unitconverter.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unitconverter.presentation.home.components.HomeScreenSearchBar
import com.example.unitconverter.presentation.home.components.HomeScreenTopBar
import com.example.unitconverter.presentation.home.components.QuantityCard
import com.example.unitconverter.presentation.theme.getQuantityColor
import com.example.unitconverter.presentation.theme.getQuantityIcon
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    onNavigateToQuantity: (String) -> Unit
) {
    val viewModel: HomeViewModel = koinViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()
    val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        isDarkTheme = isDarkTheme,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
        onNavigateToQuantity = onNavigateToQuantity
    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    isDarkTheme: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onNavigateToQuantity: (String) -> Unit
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar()
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ) {
            HomeScreenSearchBar(
                searchQuery = state.searchQuery, onSearchQueryChange
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                state.categories.forEach { category ->
                    val quantities = state.groupedQuantities[category] ?: emptyList()

                    if (quantities.isNotEmpty()) {

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            SectionHeading(category.categoryName)
                        }

                        itemsIndexed(quantities) { index, item ->
                            QuantityCard(
                                label = item.quantityName,
                                icon = getQuantityIcon(item.id),
                                color = getQuantityColor(index),
                                isDarkTheme = isDarkTheme,
                                onClick = { onNavigateToQuantity(item.id) })
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun SectionHeading(
    text: String
) {
    Text(
        text = text, style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}