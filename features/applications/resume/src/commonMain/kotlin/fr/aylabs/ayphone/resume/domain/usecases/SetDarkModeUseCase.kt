package fr.aylabs.ayphone.resume.domain.usecases

import fr.aylabs.settings.domain.enums.SettingsKeys
import fr.aylabs.settings.domain.usecases.SetSettingUseCase

class SetDarkModeUseCase(private val setSettingUseCase: SetSettingUseCase) {
    operator fun invoke(enabled: Boolean) {
        return setSettingUseCase(SettingsKeys.DARK_MODE_ENABLED, enabled)
    }
}