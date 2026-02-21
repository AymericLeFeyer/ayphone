package fr.aylabs.ayphone.hobbies.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface HobbiesRoutes {
    @Serializable
    @SerialName("HobbiesGraph")
    data object Graph : HobbiesRoutes

    @Serializable
    @SerialName("Hobbies")
    data object Root : HobbiesRoutes
}
