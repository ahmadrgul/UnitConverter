package com.example.unitconverter.di

import com.example.unitconverter.data.repository.FavouritesRepositoryImpl
import com.example.unitconverter.domain.repository.FavouritesRepository
import com.example.unitconverter.domain.usecase.ConvertUnitUseCase
import com.example.unitconverter.presentation.converter.ConverterViewModel
import com.example.unitconverter.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(platformModule)

    single { ConvertUnitUseCase() }
    viewModelOf(::ConverterViewModel)
    viewModelOf(::HomeViewModel)

    single<FavouritesRepository> { FavouritesRepositoryImpl(dataStore = get()) }
}