package fr.aylabs.ayphone.frame.interfaces.ui

import ApplicationLogo
import AySpacings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.navigation.NavHostController
import fr.aylabs.ayphone.about.ui.navigation.AboutRoutes
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.ayshop.ui.navigation.AyShopRoutes
import fr.aylabs.ayphone.clients.ui.navigation.ClientsRoutes
import fr.aylabs.ayphone.missions.ui.navigation.MissionsRoutes
import fr.aylabs.ayphone.settings.ui.navigation.SettingsRoutes
import fr.aylabs.ayphone.sideprojects.ui.navigation.SideProjectsRoutes
import fr.aylabs.ayphone.stack.ui.navigation.StackRoutes
import fr.aylabs.ayphone.timeline.TimelineConfig
import fr.aylabs.ayphone.travel.TravelConfig
import fr.aylabs.ayphone.widget.PhotoWidget

@Composable
fun Frame(
    navController: NavHostController,
    installedApps: Set<String> = emptySet(),
    showAppTitles: Boolean = false,
) {
    val uriHandler = LocalUriHandler.current
    val apps = buildList<AyApp> {
        add(AyApp.MISSIONS)
        add(AyApp.AYSHOP)
        add(AyApp.STACK)
        add(AyApp.ABOUT)
        add(AyApp.CLIENTS)
        add(AyApp.TIMELINE)
        add(AyApp.SETTINGS)
        if (AyApp.TRAVEL.id in installedApps) add(AyApp.TRAVEL)
        if (AyApp.SIDE_PROJECTS.id in installedApps) add(AyApp.SIDE_PROJECTS)
    }
    val headerApps = apps.take(4)
    val restApps = apps.drop(4)
    val widget = remember { PhotoWidget() }
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
                    widget.Content()
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(AySpacings.s),
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                        headerApps.getOrNull(0)?.let { ApplicationLogo(it, Modifier.weight(1f), showTitle = showAppTitles, onClick = { navigateTo(navController, uriHandler, it) }) }
                        headerApps.getOrNull(1)?.let { ApplicationLogo(it, Modifier.weight(1f), showTitle = showAppTitles, onClick = { navigateTo(navController, uriHandler, it) }) }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                        headerApps.getOrNull(2)?.let { ApplicationLogo(it, Modifier.weight(1f), showTitle = showAppTitles, onClick = { navigateTo(navController, uriHandler, it) }) }
                        headerApps.getOrNull(3)?.let { ApplicationLogo(it, Modifier.weight(1f), showTitle = showAppTitles, onClick = { navigateTo(navController, uriHandler, it) }) }
                    }
                }
            }
        }
        items(restApps.size) { index ->
            val app = restApps[index]
            ApplicationLogo(app, showTitle = showAppTitles, onClick = { navigateTo(navController, uriHandler, app) })
        }
    }
}

private fun navigateTo(navController: NavHostController, uriHandler: UriHandler, app: AyApp) {
    when (app) {
        AyApp.MISSIONS -> navController.navigate(MissionsRoutes.Root())
        AyApp.STACK -> navController.navigate(StackRoutes.Root)
        AyApp.ABOUT -> navController.navigate(AboutRoutes.Root)
        AyApp.CLIENTS -> navController.navigate(ClientsRoutes.Root)
        AyApp.SIDE_PROJECTS -> navController.navigate(SideProjectsRoutes.Root())
        AyApp.AYSHOP -> navController.navigate(AyShopRoutes.Root)
        AyApp.TIMELINE -> uriHandler.openUri(TimelineConfig.URL)
        AyApp.TRAVEL -> uriHandler.openUri(TravelConfig.URL)
        AyApp.SETTINGS -> navController.navigate(SettingsRoutes.Root)
    }
}
