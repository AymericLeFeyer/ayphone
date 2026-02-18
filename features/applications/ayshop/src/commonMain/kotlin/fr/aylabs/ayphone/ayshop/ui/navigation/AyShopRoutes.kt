package fr.aylabs.ayphone.ayshop.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AyShopRoutes {
    @Serializable
    @SerialName("AyShopGraph")
    data object Graph : AyShopRoutes

    @Serializable
    @SerialName("AyShop")
    data object Root : AyShopRoutes

    @Serializable
    @SerialName("AppDetail")
    data class AppDetail(val appId: String) : AyShopRoutes
}
