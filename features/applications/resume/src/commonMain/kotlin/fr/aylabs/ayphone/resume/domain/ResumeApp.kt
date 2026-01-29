package fr.aylabs.ayphone.resume.domain

import androidx.navigation.NavController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.resume.ui.navigation.ResumeRoutes

class ResumeApp(private val navController: NavController) : Application(title = "Resume") {
    override fun onClick() {
        navController.navigate(ResumeRoutes.Root)
    }
}