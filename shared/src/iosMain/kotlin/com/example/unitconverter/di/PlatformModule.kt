package com.example.unitconverter.di

import com.example.unitconverter.core.utils.ClipboardService
import com.example.unitconverter.core.utils.IosClipboardService
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<ClipboardService> { IosClipboardService() }
}