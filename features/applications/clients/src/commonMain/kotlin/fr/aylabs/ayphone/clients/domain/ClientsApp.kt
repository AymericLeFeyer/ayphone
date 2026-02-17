package fr.aylabs.ayphone.clients.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.clients.ui.navigation.ClientsRoutes

class ClientsApp(private val navController: NavController) : Application(title = "Clients") {
    override fun onClick() {
        navController.navigate(ClientsRoutes.Root)
    }
}
