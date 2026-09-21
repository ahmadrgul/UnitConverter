package com.example.unitconverter.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.unitconverter.core.utils.ClipboardService
import com.example.unitconverter.core.utils.IosClipboardService
import com.example.unitconverter.database.UnitConverterDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual val platformModule: Module = module {
    single<ClipboardService> { IosClipboardService() }

    single<DataStore<Preferences>>(named(DataStoreQualifier.FAVOURITES)) {
        createDataStore(producePath = {
            val documentDir: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(documentDir).path + "/favourites.preferences_pb"
        })
    }

    single<DataStore<Preferences>>(named(DataStoreQualifier.SETTINGS)) {
        createDataStore(producePath = {
            val documentDir: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(documentDir).path + "/settings.preferences_pb"
        })
    }

    single<SqlDriver> {
        NativeSqliteDriver(
            schema = UnitConverterDatabase.Schema,
            name = "unitconverter.db"
        )
    }
}