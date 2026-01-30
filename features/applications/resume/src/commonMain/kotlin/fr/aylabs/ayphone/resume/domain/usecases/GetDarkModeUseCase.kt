package fr.aylabs.ayphone.resume.domain.usecases

import fr.aylabs.settings.domain.enums.SettingsKeys
import fr.aylabs.settings.domain.usecases.GetSettingUseCase

class GetDarkModeUseCase(private val getSettingUseCase: GetSettingUseCase) {
    operator fun invoke(): Boolean {
        return getSettingUseCase(SettingsKeys.DARK_MODE_ENABLED, false)
    }
}