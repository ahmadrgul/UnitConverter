package com.example.unitconverter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.unitconverter.core.utils.AndroidClipboardService
import com.example.unitconverter.core.utils.ClipboardService
import com.example.unitconverter.database.UnitConverterDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<ClipboardService> { AndroidClipboardService(context = get()) }

    single<DataStore<Preferences>> {
        val context: Context = get()
        createDataStore(
            producePath = { context.filesDir.resolve("favourites.preferences_pb").absolutePath }
        )
    }

    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = UnitConverterDatabase.Schema,
            context = get(),
            name = "unitconverter.db"
        )
    }
}