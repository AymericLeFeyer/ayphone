package fr.aylabs.ayphone.clients.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.clients.ui.navigation.ClientsRoutes

class ClientsApp(private val navController: NavController) : Application(
    title = "Clients",
    iconEmoji = "🤝",
    iconColor = 0xFFB45309,
) {
    override fun onClick() {
        navController.navigate(ClientsRoutes.Root)
    }
}
