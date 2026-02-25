package fr.aylabs.ayphone.about.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AboutRoutes {
    @Serializable
    @SerialName("about-graph")
    data object Graph : AboutRoutes

    @Serializable
    @SerialName("about")
    data object Root : AboutRoutes
}
