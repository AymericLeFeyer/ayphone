package fr.aylabs.ayphone.frame.interfaces.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import fr.aylabs.ayphone.about.ui.navigation.AboutRoutes
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.application.ui.ApplicationLogo
import fr.aylabs.ayphone.aylabs.ui.navigation.AyLabsRoutes
import fr.aylabs.ayphone.ayshop.ui.navigation.AyShopRoutes
import fr.aylabs.ayphone.clients.ui.navigation.ClientsRoutes
import fr.aylabs.ayphone.hobbies.ui.navigation.HobbiesRoutes
import fr.aylabs.ayphone.missions.ui.navigation.MissionsRoutes
import fr.aylabs.ayphone.settings.AppPreferences
import fr.aylabs.ayphone.settings.ui.navigation.SettingsRoutes
import fr.aylabs.ayphone.sideprojects.ui.navigation.SideProjectsRoutes
import fr.aylabs.ayphone.stack.ui.navigation.StackRoutes
import fr.aylabs.ayphone.timeline.TimelineConfig
import fr.aylabs.ayphone.travel.TravelConfig
import fr.aylabs.ayphone.widget.PhotoWidget
import fr.aylabs.ayphone.widget.TextWidget
import fr.aylabs.ayphone.search.ui.SpotlightSearch
import androidx.compose.ui.unit.dp
import fr.aylabs.design_system.AySpacings
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Frame(
    navController: NavHostController,
    frameViewModel: FrameViewModel = koinViewModel(),
    appsPreferences: AppPreferences = koinInject()
) {
    val uiState by frameViewModel.state.collectAsStateWithLifecycle()
    when (val state = uiState) {
        is FrameState.Initial,
        is FrameState.Loading -> {
            CircularProgressIndicator()
        }

        is FrameState.Error -> {
            Text(state.error.toString())
        }

        is FrameState.Success -> {
            val uriHandler = LocalUriHandler.current
            val apps = buildList<AyApp> {
                add(AyApp.ABOUT)
                add(AyApp.MISSIONS)
                add(AyApp.STACK)
                add(AyApp.CLIENTS)
                add(AyApp.TIMELINE)
                add(AyApp.AYLABS)
                add(AyApp.SETTINGS)
                add(AyApp.AYSHOP)

                AyApp.entries.forEach { app ->
                    if (app.id in state.installedApps) add(app)
                }

            }
            val showAppTitles by appsPreferences.showAppTitles.collectAsStateWithLifecycle()

            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(AySpacings.s),
                    columns = GridCells.Fixed(4),
                    userScrollEnabled = false,
                    horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
                    verticalArrangement = Arrangement.spacedBy(AySpacings.s),
                ) {
                    item(span = { GridItemSpan(4) }) {
                        Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                            Box(modifier = Modifier.weight(1f)) {
                                PhotoWidget(appTitleShown = showAppTitles)
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(AySpacings.s),
                            ) {
                                Box(modifier = Modifier.fillMaxWidth().aspectRatio(2f)) {
                                    TextWidget(
                                        state.name,
                                        state.role,
                                        appTitleShown = showAppTitles
                                    )
                                }
                                Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                                    apps.take(2).forEachIndexed { index, app ->
                                        ApplicationLogo(
                                            app,
                                            Modifier.weight(1f),
                                            showTitle = showAppTitles,
                                            animationDelayMs = index * 60,
                                            onClick = { navigateTo(navController, uriHandler, app) })
                                    }
                                }
                            }
                        }
                    }
                    apps.drop(2).let { otherApps ->
                        items(otherApps.size) { index ->
                            val app = otherApps[index]
                            ApplicationLogo(
                                app,
                                showTitle = showAppTitles,
                                animationDelayMs = (index + 2) * 60,
                                onClick = { navigateTo(navController, uriHandler, app) })
                        }
                    }
                    // Spacer to make room for the bottom search bar overlay
                    item(span = { GridItemSpan(4) }) {
                        Spacer(Modifier.height(72.dp))
                    }
                }

                SpotlightSearch(
                    modifier = Modifier.fillMaxSize(),
                    onOpenApp = { app -> navigateTo(navController, uriHandler, app) },
                    onOpenMission = { index -> navController.navigate(MissionsRoutes.MissionDetail(index)) },
                    onOpenSkill = { label -> navController.navigate(StackRoutes.SkillDetail(label)) },
                    onOpenClient = { name -> navController.navigate(ClientsRoutes.ClientDetail(name)) },
                    onOpenSideProject = { index -> navController.navigate(SideProjectsRoutes.MissionDetail(index)) },
                )
            }
        }
    }
}

private fun navigateTo(navController: NavHostController, uriHandler: UriHandler, app: AyApp) {
    when (app) {
        AyApp.MISSIONS -> navController.navigate(MissionsRoutes.Root())
        AyApp.STACK -> navController.navigate(StackRoutes.Root)
        AyApp.ABOUT -> navController.navigate(AboutRoutes.Root)
        AyApp.AYLABS -> navController.navigate(AyLabsRoutes.Root)
        AyApp.HOBBIES -> navController.navigate(HobbiesRoutes.Root)
        AyApp.CLIENTS -> navController.navigate(ClientsRoutes.Root)
        AyApp.SIDE_PROJECTS -> navController.navigate(SideProjectsRoutes.Root())
        AyApp.AYSHOP -> navController.navigate(AyShopRoutes.Root)
        AyApp.TIMELINE -> uriHandler.openUri(TimelineConfig.URL)
        AyApp.TRAVEL -> uriHandler.openUri(TravelConfig.URL)
        AyApp.SETTINGS -> navController.navigate(SettingsRoutes.Root)
    }
}
