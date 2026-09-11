package com.example.unitconverter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.unitconverter.core.utils.AndroidClipboardService
import com.example.unitconverter.core.utils.ClipboardService
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<ClipboardService> { AndroidClipboardService(context = get()) }

    single<DataStore<Preferences>>  {
        val context: Context = get()
        createDataStore(
            producePath = { context.filesDir.resolve("settings.preferences_pb").absolutePath }
        )
    }
}