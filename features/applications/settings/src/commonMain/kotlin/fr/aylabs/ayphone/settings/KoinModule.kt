package fr.aylabs.ayphone.settings

import fr.aylabs.ayphone.settings.ui.viewmodels.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val SETTINGS_APP_MODULE = module {
    single { AppPreferences(getSettingUseCase = get(), setSettingUseCase = get()) }
    viewModel { SettingsViewModel(appPreferences = get(), installationRepository = get()) }
}
