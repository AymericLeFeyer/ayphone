package fr.aylabs.ayphone.settings.ui.navigation

import kotlinx.serialization.Serializable

sealed interface SettingsRoutes {
    @Serializable
    data object Graph : SettingsRoutes

    @Serializable
    data object Root : SettingsRoutes
}
