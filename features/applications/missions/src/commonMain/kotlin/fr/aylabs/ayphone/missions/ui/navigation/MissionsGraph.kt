package fr.aylabs.ayphone.missions.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import fr.aylabs.ayphone.missions.ui.screens.MissionDetailScreen
import fr.aylabs.ayphone.missions.ui.screens.MissionsScreen
import fr.aylabs.ayphone.missions.ui.viewmodels.MissionsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.missionsGraph(
    navController: NavController,
    onSeeSkillDetail: (String) -> Unit = {},
    onCompanyClick: (String) -> Unit = {},
) {
    navigation<MissionsRoutes.Graph>(startDestination = MissionsRoutes.Root()) {
        composable<MissionsRoutes.Root> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(MissionsRoutes.Graph)
            }
            val vm: MissionsViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            val route = backStackEntry.toRoute<MissionsRoutes.Root>()

            LaunchedEffect(route.initialSkillFilter) {
                vm.setInitialSkillFilter(route.initialSkillFilter)
            }

            MissionsScreen(
                onBackClick = navController::popBackStack,
                onMissionClick = { index -> navController.navigate(MissionsRoutes.MissionDetail(index)) },
                vm = vm,
            )
        }
        composable<MissionsRoutes.MissionDetail> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(MissionsRoutes.Graph)
            }
            val vm: MissionsViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            val route = backStackEntry.toRoute<MissionsRoutes.MissionDetail>()
            MissionDetailScreen(
                missionIndex = route.missionIndex,
                vm = vm,
                onBackClick = navController::popBackStack,
                onSeeSkillDetail = onSeeSkillDetail,
                onCompanyClick = onCompanyClick,
            )
        }
    }
}
