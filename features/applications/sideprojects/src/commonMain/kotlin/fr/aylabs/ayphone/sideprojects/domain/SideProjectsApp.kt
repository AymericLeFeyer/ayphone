package fr.aylabs.ayphone.sideprojects.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.sideprojects.ui.navigation.SideProjectsRoutes

class SideProjectsApp(private val navController: NavController) : Application(title = "Side Projects") {
    override fun onClick() {
        navController.navigate(SideProjectsRoutes.Root())
    }
}
