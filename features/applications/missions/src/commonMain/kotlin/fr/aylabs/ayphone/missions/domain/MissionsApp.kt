package fr.aylabs.ayphone.missions.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.missions.ui.navigation.MissionsRoutes

class MissionsApp(private val navController: NavController) : Application(
    title = "Missions",
    iconEmoji = "🚀",
    iconColor = 0xFF1E40AF,
) {
    override fun onClick() {
        navController.navigate(MissionsRoutes.Root())
    }
}
