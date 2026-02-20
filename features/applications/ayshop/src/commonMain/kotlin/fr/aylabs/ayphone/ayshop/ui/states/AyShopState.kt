package fr.aylabs.ayphone.ayshop.ui.states

import fr.aylabs.ayphone.application.data.AyApp

sealed class AyShopState {
    data object Loading : AyShopState()

    data class Success(
        val apps: List<AyApp>,
        val installedApps: Set<String>,
        val installingApps: Set<String>,
    ) : AyShopState() {
        fun isInstalled(appId: String) = appId in installedApps
        fun isInstalling(appId: String) = appId in installingApps
    }
}
