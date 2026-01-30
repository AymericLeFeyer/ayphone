package fr.aylabs.settings.domain.usecases

import fr.aylabs.settings.domain.enums.SettingsKeys
import fr.aylabs.settings.domain.repositories.SettingsRepository

class GetSettingUseCase(private val settingsRepository: SettingsRepository) {
    operator fun invoke(key: SettingsKeys, defaultValue: String? = null): String {
        return settingsRepository.get(key, defaultValue)
    }

    operator fun invoke(key: SettingsKeys, defaultValue: Boolean? = null): Boolean {
        return settingsRepository.get(key, defaultValue)
    }
}