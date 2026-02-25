package fr.aylabs.ayphone.hobbies.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface HobbiesRoutes {
    @Serializable
    @SerialName("hobbies-graph")
    data object Graph : HobbiesRoutes

    @Serializable
    @SerialName("hobbies")
    data object Root : HobbiesRoutes
}
