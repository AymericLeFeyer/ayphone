package fr.aylabs.ayphone.stack.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.aylabs.ayphone.stack.ui.screens.StackScreen
import fr.aylabs.ayphone.stack.ui.viewmodels.StackViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.stackGraph(
    navController: NavController
) {
    navigation<StackRoutes.Graph>(startDestination = StackRoutes.Root) {
        composable<StackRoutes.Root> {
            val vm: StackViewModel = koinViewModel()
            StackScreen(
                onBackClick = navController::popBackStack,
                vm = vm,
            )
        }
    }
}
