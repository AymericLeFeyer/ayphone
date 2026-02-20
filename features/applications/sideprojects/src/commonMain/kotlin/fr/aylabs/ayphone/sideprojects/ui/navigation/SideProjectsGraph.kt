package fr.aylabs.ayphone.sideprojects.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import fr.aylabs.ayphone.missions.ui.screens.MissionDetailScreen
import fr.aylabs.ayphone.missions.ui.screens.MissionsScreen
import fr.aylabs.ayphone.sideprojects.ui.viewmodels.SideProjectsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.sideProjectsGraph(
    navController: NavController,
    containerColor: Color,
    onSeeSkillDetail: (String) -> Unit = {},
    onCompanyClick: (String) -> Unit = {},
) {
    navigation<SideProjectsRoutes.Graph>(startDestination = SideProjectsRoutes.Root()) {
        composable<SideProjectsRoutes.Root> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(SideProjectsRoutes.Graph)
            }
            val vm: SideProjectsViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            val route = backStackEntry.toRoute<SideProjectsRoutes.Root>()

            LaunchedEffect(route.initialSkillFilter) {
                vm.setInitialSkillFilter(route.initialSkillFilter)
            }

            MissionsScreen(
                title = "Side Projects",
                onBackClick = navController::popBackStack,
                onMissionClick = { index -> navController.navigate(SideProjectsRoutes.MissionDetail(index)) },
                vm = vm,
                containerColor = containerColor,
            )
        }
        composable<SideProjectsRoutes.MissionDetail> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(SideProjectsRoutes.Graph)
            }
            val vm: SideProjectsViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            val route = backStackEntry.toRoute<SideProjectsRoutes.MissionDetail>()
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
