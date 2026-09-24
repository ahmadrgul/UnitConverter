package com.example.unitconverter.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unitconverter.presentation.home.components.HomeScreenSearchBar
import com.example.unitconverter.presentation.home.components.HomeScreenTopBar
import com.example.unitconverter.presentation.home.components.QuantityCard
import com.example.unitconverter.presentation.theme.getQuantityColor
import com.example.unitconverter.presentation.theme.getQuantityIcon
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.unitconverter.domain.model.quantity.unit.QuantityUnit
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.chevron_right_icon
import com.example.unitconverter.generated.resources.search_icon
import com.example.unitconverter.presentation.theme.getSettingsItemIconColor
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeRoute(
    onNavigateToQuantity: (String, String?) -> Unit
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
    onNavigateToQuantity: (String, String?) -> Unit
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar()
        },
    ) { innerPadding ->
        val keyboardController = LocalSoftwareKeyboardController.current
        var showSearchResults  by remember { mutableStateOf(false) }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                }
        ) {

            HomeScreenSearchBar(
                searchQuery = state.searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                clearFocus = { focusManager.clearFocus() },
                onFocusChange = {
                    if (it) {
                        showSearchResults = true
                    }
                }
            )

            if (showSearchResults) {
                SearchResults(
                    searchedUnits = state.searchedUnits,
                    onNavigateToUnit = { quantityId, unitName -> onNavigateToQuantity(quantityId, unitName) }
                )

            } else {
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
                                    onClick = { onNavigateToQuantity(item.id, null) })
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

@Composable
fun SearchResults(
    searchedUnits: Map<String, List<SearchedUnitItem>>,
    onNavigateToUnit: (String, String) -> Unit,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        searchedUnits.entries.forEach {
            item {
                Text(
                    text = it.key,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            itemsIndexed(
                items = it.value,
                key = { _, item -> item.unitName }
            ) { index, item ->
                Row (
                    modifier = Modifier
                        .animateItem()
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .dropShadow(
                            shape = RoundedCornerShape(12.dp),
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.05f),
                                radius = 4.dp,
                                spread = 0.dp
                            )
                        )
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(onClick = {onNavigateToUnit(item.quantityId, item.unitName)})
                        .padding(16.dp),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = getQuantityColor(index).copy(alpha = 0.08f),
                                    shape = RoundedCornerShape(10.dp),
                                )
                                .padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(getQuantityIcon(item.quantityId)),
                                contentDescription = "${item.unitName} icon",
                                tint = getQuantityColor(index),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column (
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically)
                        ) {
                            Text(
                                text = item.unitName,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )

                            Text(
                                text = item.quantityName,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                fontSize = 13.sp,
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            painter = painterResource(Res.drawable.chevron_right_icon),
                            contentDescription = "Chevron Right",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }

        if (searchedUnits.isEmpty()) {
            item {
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .padding(vertical = 32.dp)
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(0.05f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical =20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                                shape = RoundedCornerShape(10.dp),
                            )
                            .padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.search_icon),
                            contentDescription = "Search icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column (
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Can't find what you need?",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Try searching with a different keyword.",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}