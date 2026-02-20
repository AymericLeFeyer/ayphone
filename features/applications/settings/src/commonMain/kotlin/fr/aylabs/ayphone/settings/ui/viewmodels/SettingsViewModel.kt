package fr.aylabs.ayphone.settings.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.settings.AppPreferences
import fr.aylabs.ayphone.settings.AppTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

sealed class DevModeEvent {
    data class Countdown(val remaining: Int) : DevModeEvent()
    data object Activated : DevModeEvent()
    data object AlreadyActive : DevModeEvent()
}

class SettingsViewModel(private val appPreferences: AppPreferences) : ViewModel() {

    val theme: StateFlow<AppTheme> = appPreferences.theme
    val showAppTitles: StateFlow<Boolean> = appPreferences.showAppTitles
    val developerMode: StateFlow<Boolean> = appPreferences.developerMode

    private val _devTapCount = MutableStateFlow(0)
    private val _devModeEvents = MutableSharedFlow<DevModeEvent>()
    val devModeEvents: SharedFlow<DevModeEvent> = _devModeEvents.asSharedFlow()

    fun setTheme(theme: AppTheme) = appPreferences.setTheme(theme)

    fun setShowAppTitles(value: Boolean) = appPreferences.setShowAppTitles(value)

    fun disableDeveloperMode() = appPreferences.setDeveloperMode(false)

    fun onBuildTap() {
        viewModelScope.launch {
            if (appPreferences.developerMode.value) {
                _devModeEvents.emit(DevModeEvent.AlreadyActive)
                return@launch
            }
            val count = _devTapCount.value + 1
            _devTapCount.value = count
            val remaining = 7 - count
            if (remaining > 0) {
                _devModeEvents.emit(DevModeEvent.Countdown(remaining))
            } else {
                appPreferences.setDeveloperMode(true)
                _devTapCount.value = 0
                _devModeEvents.emit(DevModeEvent.Activated)
            }
        }
    }
}
