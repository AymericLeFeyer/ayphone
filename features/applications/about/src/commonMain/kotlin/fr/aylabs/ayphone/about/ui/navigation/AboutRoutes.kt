package fr.aylabs.ayphone.about.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AboutRoutes {
    @Serializable
    @SerialName("AboutGraph")
    data object Graph : AboutRoutes

    @Serializable
    @SerialName("About")
    data object Root : AboutRoutes
}
