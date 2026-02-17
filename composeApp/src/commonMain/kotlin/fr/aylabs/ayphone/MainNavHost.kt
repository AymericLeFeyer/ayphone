package fr.aylabs.ayphone

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import fr.aylabs.ayphone.about.ui.navigation.aboutGraph
import fr.aylabs.ayphone.frame.interfaces.ui.Frame
import fr.aylabs.ayphone.missions.ui.navigation.MissionsRoutes
import fr.aylabs.ayphone.missions.ui.navigation.missionsGraph
import fr.aylabs.ayphone.stack.ui.navigation.stackGraph
import kotlinx.serialization.Serializable

sealed interface MainRoutes {
    @Serializable
    data object Root : MainRoutes
}

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: MainRoutes = MainRoutes.Root,
    onBackClick: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        modifier = modifier,
    ) {
        composable<MainRoutes.Root> {
            Frame(navController)
        }

        missionsGraph(navController)
        stackGraph(
            navController = navController,
            onSeeRelatedMissions = { skillName ->
                navController.navigate(MissionsRoutes.Root(initialSkillFilter = skillName))
            },
        )
        aboutGraph(navController)
    }
}
