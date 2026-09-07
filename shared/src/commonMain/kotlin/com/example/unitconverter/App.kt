package com.example.unitconverter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverter.home.HomeScreen
import com.example.unitconverter.navigation.AppNavigation
import com.example.unitconverter.theme.UnitConverterTheme

@Composable
@Preview
fun App() {
    UnitConverterTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation()
        }
    }
}