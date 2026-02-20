package fr.aylabs.ayphone.settings.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.aylabs.ayphone.settings.ui.screens.SettingsScreen
import fr.aylabs.ayphone.settings.ui.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.settingsGraph(navController: NavController) {
    navigation<SettingsRoutes.Graph>(startDestination = SettingsRoutes.Root) {
        composable<SettingsRoutes.Root> {
            val vm: SettingsViewModel = koinViewModel()
            SettingsScreen(
                onBackClick = navController::popBackStack,
                vm = vm,
            )
        }
    }
}
