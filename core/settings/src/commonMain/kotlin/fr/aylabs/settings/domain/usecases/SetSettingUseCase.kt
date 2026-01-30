package fr.aylabs.settings.domain.usecases

import fr.aylabs.settings.domain.enums.SettingsKeys
import fr.aylabs.settings.domain.repositories.SettingsRepository

class SetSettingUseCase(private val settingsRepository: SettingsRepository) {
    operator fun invoke(key: SettingsKeys, value: String) {
        return settingsRepository.set(key, value)
    }

    operator fun invoke(key: SettingsKeys, value: Boolean) {
        return settingsRepository.set(key, value)
    }
}