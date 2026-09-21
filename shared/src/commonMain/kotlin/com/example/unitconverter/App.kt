package com.example.unitconverter

import androidx.compose.runtime.Composable
import com.example.unitconverter.di.appModule
import com.example.unitconverter.presentation.app.AppContent
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModule) })
    ) {
        AppContent()
    }
}
