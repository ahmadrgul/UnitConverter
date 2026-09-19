package com.example.unitconverter.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unitconverter.domain.model.settings.SettingsItem
import com.example.unitconverter.presentation.settings.components.SettingsScreenTopBar
import com.example.unitconverter.presentation.theme.getSettingsItemIcon
import com.example.unitconverter.presentation.theme.getSettingsItemIconColor
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import com.example.unitconverter.presentation.settings.components.DynamicItemRenderer
import com.example.unitconverter.presentation.settings.components.SettingsGroup
import com.example.unitconverter.presentation.settings.components.SettingsItemCard

@Composable
fun SettingsRoute(){
    val viewModel: SettingsViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsScreen(
        state = state,
        toggleDarkTheme = { viewModel.toggleDarkTheme() },
        toggleHistory = { viewModel.toggleHistory() },
        setAppLanguage = { viewModel.setAppLanguage(it) },
        setPrecision = { viewModel.setDecimalPrecision(it) }
    )
}

@Composable
fun SettingsScreen(
    state: SettingsState,
    toggleDarkTheme: () -> Unit,
    toggleHistory: () -> Unit,
    setAppLanguage: (String) -> Unit,
    setPrecision: (Int) -> Unit
){
    Scaffold(
        topBar = { SettingsScreenTopBar() }
    ) { innerPadding ->

        val verticalScroll = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .verticalScroll(verticalScroll)
                .padding(horizontal = 16.dp)
        ) {

            state.items.keys.forEach { title ->
                SettingsGroup(
                    state = state,
                    title = title,
                    items = state.items[title] ?: emptyList(),
                    toggleDarkTheme = toggleDarkTheme,
                    toggleHistory = toggleHistory,
                    setAppLanguage = setAppLanguage,
                    setPrecision = setPrecision
                )
            }

        }
    }
}
