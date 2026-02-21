package fr.aylabs.ayphone.aylabs.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AyLabsRoutes {
    @Serializable
    @SerialName("AyLabsGraph")
    data object Graph : AyLabsRoutes

    @Serializable
    @SerialName("AyLabs")
    data object Root : AyLabsRoutes
}
