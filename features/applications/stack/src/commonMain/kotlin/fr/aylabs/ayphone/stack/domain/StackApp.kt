package fr.aylabs.ayphone.stack.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.stack.ui.navigation.StackRoutes

class StackApp(private val navController: NavController) : Application(title = "Stack") {
    override fun onClick() {
        navController.navigate(StackRoutes.Graph)
    }
}
