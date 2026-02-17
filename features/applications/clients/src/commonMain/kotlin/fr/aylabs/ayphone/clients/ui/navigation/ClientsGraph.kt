package fr.aylabs.ayphone.clients.ui.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import fr.aylabs.ayphone.clients.ui.screens.ClientDetailScreen
import fr.aylabs.ayphone.clients.ui.screens.ClientsScreen
import fr.aylabs.ayphone.clients.ui.viewmodels.ClientsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.clientsGraph(
    navController: NavController,
    onSeeSkillDetail: (String) -> Unit = {},
    onSeeMission: (Int) -> Unit = {},
) {
    navigation<ClientsRoutes.Graph>(startDestination = ClientsRoutes.Root) {
        composable<ClientsRoutes.Root> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ClientsRoutes.Graph)
            }
            val vm: ClientsViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            ClientsScreen(
                onBackClick = navController::popBackStack,
                onClientClick = { companyName ->
                    navController.navigate(ClientsRoutes.ClientDetail(companyName))
                },
                vm = vm,
            )
        }
        composable<ClientsRoutes.ClientDetail> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ClientsRoutes.Graph)
            }
            val vm: ClientsViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            val route = backStackEntry.toRoute<ClientsRoutes.ClientDetail>()
            ClientDetailScreen(
                companyName = route.companyName,
                vm = vm,
                onBackClick = navController::popBackStack,
                onSeeSkillDetail = onSeeSkillDetail,
                onSeeMission = onSeeMission,
            )
        }
    }
}
