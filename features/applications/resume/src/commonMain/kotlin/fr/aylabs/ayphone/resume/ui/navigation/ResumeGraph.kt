package fr.aylabs.ayphone.resume.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.aylabs.ayphone.resume.ui.screens.ResumeScreen

fun NavGraphBuilder.resumeGraph(
    navController: NavController
) {
    composable<ResumeRoutes.Root> {
        ResumeScreen(onBackClick = navController::popBackStack)
    }
}