package fr.aylabs.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings
import org.koin.dsl.module

actual fun createSettingsModule() = module {
    single<Settings> { StorageSettings() }
}