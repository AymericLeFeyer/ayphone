package fr.aylabs.ayphone.aylabs.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AyLabsRoutes {
    @Serializable
    @SerialName("aylabs-graph")
    data object Graph : AyLabsRoutes

    @Serializable
    @SerialName("aylabs")
    data object Root : AyLabsRoutes
}
