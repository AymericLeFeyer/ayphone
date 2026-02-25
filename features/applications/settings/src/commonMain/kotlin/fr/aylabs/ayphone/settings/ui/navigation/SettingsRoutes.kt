package fr.aylabs.ayphone.settings.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface SettingsRoutes {
    @Serializable
    @SerialName("settings-graph")
    data object Graph : SettingsRoutes

    @Serializable
    @SerialName("settings")
    data object Root : SettingsRoutes
}
