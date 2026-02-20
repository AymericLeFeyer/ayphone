package fr.aylabs.ayphone.about.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.aylabs.ayphone.about.ui.screens.AboutScreen
import fr.aylabs.ayphone.about.ui.viewmodels.AboutViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.aboutGraph(
    navController: NavController,
) {
    navigation<AboutRoutes.Graph>(startDestination = AboutRoutes.Root) {
        composable<AboutRoutes.Root> {
            val vm: AboutViewModel = koinViewModel()
            AboutScreen(
                onBackClick = navController::popBackStack,
                vm = vm,
            )
        }
    }
}
