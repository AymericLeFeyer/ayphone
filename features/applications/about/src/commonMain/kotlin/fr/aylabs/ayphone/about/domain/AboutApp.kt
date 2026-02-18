package fr.aylabs.ayphone.about.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.about.ui.navigation.AboutRoutes
import fr.aylabs.ayphone.application.data.Application

class AboutApp(private val navController: NavController) : Application(
    title = "About",
    iconEmoji = "🙋",
    iconColor = 0xFF3730A3,
) {
    override fun onClick() {
        navController.navigate(AboutRoutes.Root)
    }
}
