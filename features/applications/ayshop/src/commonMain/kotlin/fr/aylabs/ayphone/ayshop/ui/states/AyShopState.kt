package fr.aylabs.ayphone.ayshop.ui.states

import fr.aylabs.ayphone.application.data.Application

sealed class AyShopState {
    data object Loading : AyShopState()

    data class Success(
        val apps: List<Application>,
        val installedApps: Set<String>,
        val installingApps: Set<String>,
    ) : AyShopState() {
        fun isInstalled(appId: String) = appId in installedApps
        fun isInstalling(appId: String) = appId in installingApps
    }
}
