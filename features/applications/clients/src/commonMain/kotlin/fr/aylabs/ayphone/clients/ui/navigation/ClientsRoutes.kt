package fr.aylabs.ayphone.clients.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ClientsRoutes {
    @Serializable
    @SerialName("ClientsGraph")
    data object Graph : ClientsRoutes

    @Serializable
    @SerialName("Clients")
    data object Root : ClientsRoutes

    @Serializable
    @SerialName("ClientDetail")
    data class ClientDetail(val companyName: String) : ClientsRoutes
}
