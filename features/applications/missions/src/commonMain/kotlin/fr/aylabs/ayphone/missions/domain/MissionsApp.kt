package fr.aylabs.ayphone.missions.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.missions.ui.navigation.MissionsRoutes

class MissionsApp(private val navController: NavController) : Application(title = "Missions") {
    override fun onClick() {
        navController.navigate(MissionsRoutes.Root())
    }
}
