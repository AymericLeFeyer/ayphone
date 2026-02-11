package fr.aylabs.ayphone.resume.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import androidx.compose.runtime.remember
import fr.aylabs.ayphone.resume.ui.screens.MissionDetailScreen
import fr.aylabs.ayphone.resume.ui.screens.ResumeScreen
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.resumeGraph(
    navController: NavController
) {
    navigation<ResumeRoutes.Graph>(startDestination = ResumeRoutes.Root) {
        composable<ResumeRoutes.Root> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ResumeRoutes.Graph)
            }
            val vm: ResumeViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            ResumeScreen(
                onBackClick = navController::popBackStack,
                onMissionClick = { index -> navController.navigate(ResumeRoutes.MissionDetail(index)) },
                vm = vm,
            )
        }
        composable<ResumeRoutes.MissionDetail> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(ResumeRoutes.Graph)
            }
            val vm: ResumeViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            val route = backStackEntry.toRoute<ResumeRoutes.MissionDetail>()
            MissionDetailScreen(
                missionIndex = route.missionIndex,
                vm = vm,
                onBackClick = navController::popBackStack,
            )
        }
    }
}
