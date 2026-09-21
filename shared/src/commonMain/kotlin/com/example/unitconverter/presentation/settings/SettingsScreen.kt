package com.example.unitconverter.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unitconverter.presentation.settings.components.SettingsScreenTopBar
import com.example.unitconverter.presentation.settings.components.SettingsSection
import com.example.unitconverter.presentation.settings.model.SettingId
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsRoute(){
    val viewModel: SettingsViewModel = koinViewModel()
    val state by viewModel.settings.collectAsStateWithLifecycle()

    SettingsScreen(
        state = state,
        onToggleChange = { id, enabled -> viewModel.onToggleChange(id, enabled) },
        onChoiceSelect = { id, value -> viewModel.onChoiceSelect(id, value) }
    )
}

@Composable
fun SettingsScreen(
    state: SettingsState,
    onToggleChange: (SettingId, Boolean) -> Unit,
    onChoiceSelect: (SettingId, String) -> Unit
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

            state.sections.forEach { section ->
                SettingsSection(
                    title = section.title,
                    items = section.items,
                    onToggleChange = onToggleChange,
                    onChoiceSelect = onChoiceSelect,
                )
            }

        }
    }
}
