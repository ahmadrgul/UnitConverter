package com.example.unitconverter.di

import com.example.unitconverter.data.repository.FavouritesRepositoryImpl
import com.example.unitconverter.data.repository.HistoryRepositoryImpl
import com.example.unitconverter.data.repository.SettingsRepositoryImpl
import com.example.unitconverter.database.UnitConverterDatabase
import com.example.unitconverter.presentation.settings.SettingsViewModel
import com.example.unitconverter.domain.repository.FavouritesRepository
import com.example.unitconverter.domain.repository.HistoryRepository
import com.example.unitconverter.domain.repository.SettingsRepository
import com.example.unitconverter.domain.usecase.ConvertUnitUseCase
import com.example.unitconverter.presentation.app.AppViewModel
import com.example.unitconverter.presentation.converter.ConverterViewModel
import com.example.unitconverter.presentation.history.HistoryViewModel
import com.example.unitconverter.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    includes(platformModule)

    single { ConvertUnitUseCase() }

    viewModel { params ->
        ConverterViewModel(
            quantityId = params.get(),
            fromUnitName = params.getOrNull(),
            historyId = params.getOrNull(),
            convertUnit = get(),
            clipboardService = get(),
            favouritesRepository = get(),
            historyRepository = get()
        )
    }

    viewModelOf(::HomeViewModel)
    viewModelOf(::HistoryViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::AppViewModel)

    single<FavouritesRepository> { FavouritesRepositoryImpl(dataStore = get(named(DataStoreQualifier.FAVOURITES))) }
    single<SettingsRepository> { SettingsRepositoryImpl(dataStore = get(named(DataStoreQualifier.SETTINGS))) }

    single { UnitConverterDatabase(driver = get()) }

    single<HistoryRepository> { HistoryRepositoryImpl(database = get()) }

}