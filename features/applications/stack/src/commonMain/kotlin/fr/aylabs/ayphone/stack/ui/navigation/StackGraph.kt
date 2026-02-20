package fr.aylabs.ayphone.stack.ui.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import fr.aylabs.ayphone.stack.ui.screens.SkillDetailScreen
import fr.aylabs.ayphone.stack.ui.screens.StackScreen
import fr.aylabs.ayphone.stack.ui.states.StackState
import fr.aylabs.ayphone.stack.ui.viewmodels.StackViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.stackGraph(
    navController: NavController,
    onSeeRelatedMissions: (String) -> Unit,
) {
    navigation<StackRoutes.Graph>(startDestination = StackRoutes.Root) {
        composable<StackRoutes.Root> {
            val vm: StackViewModel = koinViewModel()
            StackScreen(
                onBackClick = navController::popBackStack,
                onSkillClick = { skillLabel ->
                    navController.navigate(StackRoutes.SkillDetail(skillLabel))
                },
                vm = vm,
            )
        }
        composable<StackRoutes.SkillDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<StackRoutes.SkillDetail>()
            val vm: StackViewModel = koinViewModel()
            val uiState = vm.state.collectAsStateWithLifecycle()
            val resume = (uiState.value as? StackState.Success)?.data
            val resumeSkill = resume?.skills?.find { it.skill.label == route.skillLabel }

            if (resumeSkill != null) {
                SkillDetailScreen(
                    resumeSkill = resumeSkill,
                    onBackClick = navController::popBackStack,
                    onSeeRelatedMissions = {
                        onSeeRelatedMissions(resumeSkill.skill.label)
                    },
                )
            }
        }
    }
}
