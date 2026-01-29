package fr.aylabs.ayphone.resume

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.aylabs.ayphone.resume.ui.ResumeScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ResumeRoutes {
    @Serializable
    @SerialName("Resume")
    data object Root : ResumeRoutes
}

fun NavGraphBuilder.resumeGraph(
    navController: NavController
) {
    composable<ResumeRoutes.Root> {
        ResumeScreen(onBackClick = navController::popBackStack)
    }
}