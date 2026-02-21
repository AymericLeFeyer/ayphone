package fr.aylabs.ayphone.aylabs.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.aylabs.ayphone.aylabs.ui.screens.AyLabsScreen
import fr.aylabs.ayphone.aylabs.ui.viewmodels.AyLabsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.ayLabsGraph(
    navController: NavController,
) {
    navigation<AyLabsRoutes.Graph>(startDestination = AyLabsRoutes.Root) {
        composable<AyLabsRoutes.Root> {
            val vm: AyLabsViewModel = koinViewModel()
            AyLabsScreen(
                onBackClick = navController::popBackStack,
                vm = vm,
            )
        }
    }
}
