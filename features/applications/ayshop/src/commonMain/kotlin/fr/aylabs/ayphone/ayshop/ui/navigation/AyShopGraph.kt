package fr.aylabs.ayphone.ayshop.ui.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import fr.aylabs.ayphone.ayshop.ui.screens.AppDetailScreen
import fr.aylabs.ayphone.ayshop.ui.screens.AyShopScreen
import fr.aylabs.ayphone.ayshop.ui.viewmodels.AyShopViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.ayshopGraph(
    navController: NavController,
    onOpenApp: (appId: String) -> Unit = {},
) {
    navigation<AyShopRoutes.Graph>(startDestination = AyShopRoutes.Root) {
        composable<AyShopRoutes.Root> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AyShopRoutes.Graph)
            }
            val vm: AyShopViewModel = koinViewModel(viewModelStoreOwner = parentEntry)

            AyShopScreen(
                onBackClick = navController::popBackStack,
                onAppDetail = { appId -> navController.navigate(AyShopRoutes.AppDetail(appId)) },
                onOpenApp = onOpenApp,
                vm = vm,
            )
        }

        composable<AyShopRoutes.AppDetail> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AyShopRoutes.Graph)
            }
            val vm: AyShopViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            val route = backStackEntry.toRoute<AyShopRoutes.AppDetail>()

            AppDetailScreen(
                appId = route.appId,
                onBackClick = navController::popBackStack,
                onOpenApp = onOpenApp,
                vm = vm,
            )
        }
    }
}
