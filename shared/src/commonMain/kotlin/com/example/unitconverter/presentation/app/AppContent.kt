package com.example.unitconverter.presentation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unitconverter.presentation.navigation.AppNavigation
import com.example.unitconverter.presentation.theme.UnitConverterTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppContent(
    viewModel: AppViewModel = koinViewModel()
){
    val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()

    UnitConverterTheme(isDark = isDarkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation()
        }
    }
}