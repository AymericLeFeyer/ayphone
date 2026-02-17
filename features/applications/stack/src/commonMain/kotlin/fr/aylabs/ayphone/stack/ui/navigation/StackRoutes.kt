package fr.aylabs.ayphone.stack.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface StackRoutes {
    @Serializable
    @SerialName("StackGraph")
    data object Graph : StackRoutes

    @Serializable
    @SerialName("Stack")
    data object Root : StackRoutes
}
