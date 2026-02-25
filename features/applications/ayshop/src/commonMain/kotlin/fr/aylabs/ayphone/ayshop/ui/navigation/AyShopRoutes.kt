package fr.aylabs.ayphone.ayshop.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AyShopRoutes {
    @Serializable
    @SerialName("shop-graph")
    data object Graph : AyShopRoutes

    @Serializable
    @SerialName("shop")
    data object Root : AyShopRoutes

    @Serializable
    @SerialName("app-detail")
    data class AppDetail(val appId: String) : AyShopRoutes
}
