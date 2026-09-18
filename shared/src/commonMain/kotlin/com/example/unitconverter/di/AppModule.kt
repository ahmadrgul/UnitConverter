package com.example.unitconverter.di

import com.example.unitconverter.data.repository.FavouritesRepositoryImpl
import com.example.unitconverter.data.repository.HistoryRepositoryImpl
import com.example.unitconverter.database.UnitConverterDatabase
import com.example.unitconverter.presentation.settings.SettingsViewModel
import com.example.unitconverter.domain.repository.FavouritesRepository
import com.example.unitconverter.domain.repository.HistoryRepository
import com.example.unitconverter.domain.usecase.ConvertUnitUseCase
import com.example.unitconverter.presentation.converter.ConverterViewModel
import com.example.unitconverter.presentation.history.HistoryViewModel
import com.example.unitconverter.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(platformModule)

    single { ConvertUnitUseCase() }
    viewModelOf(::ConverterViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::HistoryViewModel)
    viewModelOf(::SettingsViewModel)

    single<FavouritesRepository> { FavouritesRepositoryImpl(dataStore = get()) }

    single { UnitConverterDatabase(driver = get()) }

    single<HistoryRepository> { HistoryRepositoryImpl(database = get()) }
}