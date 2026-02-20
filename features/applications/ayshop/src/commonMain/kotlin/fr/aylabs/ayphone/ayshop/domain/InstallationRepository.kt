package fr.aylabs.ayphone.ayshop.domain

import fr.aylabs.settings.domain.enums.SettingsKeys
import fr.aylabs.settings.domain.usecases.GetSettingUseCase
import fr.aylabs.settings.domain.usecases.SetSettingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InstallationRepository(
    private val getSettingUseCase: GetSettingUseCase,
    private val setSettingUseCase: SetSettingUseCase,
) {
    private val _installedApps = MutableStateFlow<Set<String>>(emptySet())
    val installedApps: StateFlow<Set<String>> = _installedApps.asStateFlow()

    init {
        val initial = mutableSetOf<String>()
        if (getSettingUseCase(SettingsKeys.SIDE_PROJECTS_INSTALLED, false)) initial.add("sideprojects")
        if (getSettingUseCase(SettingsKeys.TRAVEL_INSTALLED, false)) initial.add("travel")
        _installedApps.value = initial
    }

    fun install(appId: String) {
        persistInstallation(appId, true)
        _installedApps.update { it + appId }
    }

    fun uninstall(appId: String) {
        persistInstallation(appId, false)
        _installedApps.update { it - appId }
    }

    fun uninstallAll() {
        _installedApps.value.toSet().forEach { uninstall(it) }
    }

    private fun persistInstallation(appId: String, installed: Boolean) {
        when (appId) {
            "sideprojects" -> setSettingUseCase(SettingsKeys.SIDE_PROJECTS_INSTALLED, installed)
            "travel" -> setSettingUseCase(SettingsKeys.TRAVEL_INSTALLED, installed)
        }
    }
}
