package fr.aylabs.ayphone.clients.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ClientsRoutes {
    @Serializable
    @SerialName("clients-graph")
    data object Graph : ClientsRoutes

    @Serializable
    @SerialName("clients")
    data object Root : ClientsRoutes

    @Serializable
    @SerialName("client-detail")
    data class ClientDetail(val companyName: String) : ClientsRoutes
}
