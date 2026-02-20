package fr.aylabs.ayphone.settings

import fr.aylabs.settings.domain.enums.SettingsKeys
import fr.aylabs.settings.domain.usecases.GetSettingUseCase
import fr.aylabs.settings.domain.usecases.SetSettingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppPreferences(
    private val getSettingUseCase: GetSettingUseCase,
    private val setSettingUseCase: SetSettingUseCase,
) {
    private val _theme = MutableStateFlow(
        AppTheme.fromString(getSettingUseCase(SettingsKeys.THEME, AppTheme.SYSTEM.name))
    )
    val theme: StateFlow<AppTheme> = _theme.asStateFlow()

    private val _showAppTitles = MutableStateFlow(
        getSettingUseCase(SettingsKeys.SHOW_APP_TITLES, false)
    )
    val showAppTitles: StateFlow<Boolean> = _showAppTitles.asStateFlow()

    private val _developerMode = MutableStateFlow(
        getSettingUseCase(SettingsKeys.DEVELOPER_MODE, false)
    )
    val developerMode: StateFlow<Boolean> = _developerMode.asStateFlow()

    fun setTheme(theme: AppTheme) {
        setSettingUseCase(SettingsKeys.THEME, theme.name)
        _theme.value = theme
    }

    fun setShowAppTitles(value: Boolean) {
        setSettingUseCase(SettingsKeys.SHOW_APP_TITLES, value)
        _showAppTitles.value = value
    }

    fun setDeveloperMode(value: Boolean) {
        setSettingUseCase(SettingsKeys.DEVELOPER_MODE, value)
        _developerMode.value = value
    }
}
