package fr.aylabs.ayphone.ayshop.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.ayshop.ui.navigation.AyShopRoutes

class AyShopApp(private val navController: NavController) : Application(
    title = "AyShop",
    iconEmoji = "🛍️",
    iconColor = 0xFFBE185D,
) {
    override fun onClick() {
        navController.navigate(AyShopRoutes.Root)
    }
}
