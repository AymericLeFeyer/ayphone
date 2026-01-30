package fr.aylabs.settings

import fr.aylabs.settings.data.datasources.SettingsDatasource
import fr.aylabs.settings.data.repositories.SettingsRepositoryImpl
import fr.aylabs.settings.domain.repositories.SettingsRepository
import fr.aylabs.settings.domain.usecases.GetSettingUseCase
import fr.aylabs.settings.domain.usecases.SetSettingUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun createSettingsModule(): Module

val SETTINGS_MODULE = module {
    // Data
    single<SettingsDatasource> { SettingsDatasource(settings = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(settingsDatasource = get()) }

    // Domain
    single<GetSettingUseCase> { GetSettingUseCase(settingsRepository = get()) }
    single<SetSettingUseCase> { SetSettingUseCase(settingsRepository = get()) }
}