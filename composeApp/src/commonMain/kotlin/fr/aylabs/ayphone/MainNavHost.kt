package fr.aylabs.ayphone

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import fr.aylabs.ayphone.about.ui.navigation.aboutGraph
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.ayshop.ui.navigation.ayshopGraph
import fr.aylabs.ayphone.clients.ui.navigation.ClientsRoutes
import fr.aylabs.ayphone.clients.ui.navigation.clientsGraph
import fr.aylabs.ayphone.frame.interfaces.ui.Frame
import fr.aylabs.ayphone.missions.ui.navigation.MissionsRoutes
import fr.aylabs.ayphone.missions.ui.navigation.missionsGraph
import fr.aylabs.ayphone.settings.ui.navigation.settingsGraph
import fr.aylabs.ayphone.sideprojects.ui.navigation.SideProjectsRoutes
import fr.aylabs.ayphone.sideprojects.ui.navigation.sideProjectsGraph
import fr.aylabs.ayphone.stack.ui.navigation.StackRoutes
import fr.aylabs.ayphone.stack.ui.navigation.stackGraph
import fr.aylabs.ayphone.travel.TravelConfig
import kotlinx.serialization.Serializable

sealed interface MainRoutes {
    @Serializable
    data object Root : MainRoutes
}

private val appOpenSpec = tween<Float>(durationMillis = 350, easing = FastOutSlowInEasing)
private val appCloseSpec = tween<Float>(durationMillis = 280, easing = FastOutSlowInEasing)

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: MainRoutes = MainRoutes.Root,
    onBackClick: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val uriHandler = LocalUriHandler.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            val crossGraph =
                initialState.destination.parent?.route != targetState.destination.parent?.route
            if (crossGraph) {
                scaleIn(initialScale = 0.88f, animationSpec = appOpenSpec) + fadeIn(appOpenSpec)
            } else {
                fadeIn(tween(220))
            }
        },
        exitTransition = {
            val crossGraph =
                initialState.destination.parent?.route != targetState.destination.parent?.route
            if (crossGraph) {
                scaleOut(targetScale = 0.96f, animationSpec = appOpenSpec) + fadeOut(appOpenSpec)
            } else {
                fadeOut(tween(220))
            }
        },
        popEnterTransition = {
            val crossGraph =
                initialState.destination.parent?.route != targetState.destination.parent?.route
            if (crossGraph) {
                scaleIn(initialScale = 0.96f, animationSpec = appCloseSpec) + fadeIn(appCloseSpec)
            } else {
                fadeIn(tween(220))
            }
        },
        popExitTransition = {
            val crossGraph =
                initialState.destination.parent?.route != targetState.destination.parent?.route
            if (crossGraph) {
                scaleOut(targetScale = 0.88f, animationSpec = appCloseSpec) + fadeOut(appCloseSpec)
            } else {
                fadeOut(tween(220))
            }
        },
        modifier = modifier,
    ) {
        composable<MainRoutes.Root> {
            Frame(
                navController = navController,
            )
        }

        missionsGraph(
            navController = navController,
            containerColor = AyApp.MISSIONS.color,
            onSeeSkillDetail = { skillLabel ->
                navController.navigate(StackRoutes.SkillDetail(skillLabel))
            },
            onCompanyClick = { companyName ->
                navController.navigate(ClientsRoutes.ClientDetail(companyName))
            },
        )
        sideProjectsGraph(
            navController = navController,
            containerColor = AyApp.SIDE_PROJECTS.color,
            onSeeSkillDetail = { skillLabel ->
                navController.navigate(StackRoutes.SkillDetail(skillLabel))
            },
            onCompanyClick = { companyName ->
                navController.navigate(ClientsRoutes.ClientDetail(companyName))
            },
        )
        ayshopGraph(
            navController = navController,
            onOpenApp = { appId ->
                when (appId) {
                    AyApp.SIDE_PROJECTS.id -> navController.navigate(SideProjectsRoutes.Root())
                    AyApp.TRAVEL.id -> uriHandler.openUri(TravelConfig.URL)
                }
            },
        )
        stackGraph(
            navController = navController,
            onSeeRelatedMissions = { skillName ->
                navController.navigate(MissionsRoutes.Root(initialSkillFilter = skillName))
            },
        )
        aboutGraph(navController)
        clientsGraph(
            navController = navController,
            onSeeSkillDetail = { skillLabel ->
                navController.navigate(StackRoutes.SkillDetail(skillLabel))
            },
            onSeeMission = { missionIndex ->
                navController.navigate(MissionsRoutes.MissionDetail(missionIndex))
            },
        )
        settingsGraph(navController)
    }
}
