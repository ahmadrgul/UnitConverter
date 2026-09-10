package com.example.unitconverter.di

import com.example.unitconverter.core.utils.AndroidClipboardService
import com.example.unitconverter.core.utils.ClipboardService
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<ClipboardService> { AndroidClipboardService(context = get()) }
}