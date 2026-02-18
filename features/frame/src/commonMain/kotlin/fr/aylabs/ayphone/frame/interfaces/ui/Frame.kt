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
import androidx.navigation.NavHostController
import fr.aylabs.ayphone.about.domain.AboutApp
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.ayshop.domain.AyShopApp
import fr.aylabs.ayphone.clients.domain.ClientsApp
import fr.aylabs.ayphone.missions.domain.MissionsApp
import fr.aylabs.ayphone.sideprojects.domain.SideProjectsApp
import fr.aylabs.ayphone.stack.domain.StackApp
import fr.aylabs.ayphone.widget.PhotoWidget

@Composable
fun Frame(navController: NavHostController, installedApps: Set<String> = emptySet()) {
    val apps = buildList<Application> {
        add(MissionsApp(navController))
        add(AyShopApp(navController))
        add(StackApp(navController))
        add(AboutApp(navController))
        add(ClientsApp(navController))
        if ("sideprojects" in installedApps) add(SideProjectsApp(navController))
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
                        headerApps.getOrNull(0)?.let { ApplicationLogo(it, Modifier.weight(1f)) }
                        headerApps.getOrNull(1)?.let { ApplicationLogo(it, Modifier.weight(1f)) }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                        headerApps.getOrNull(2)?.let { ApplicationLogo(it, Modifier.weight(1f)) }
                        headerApps.getOrNull(3)?.let { ApplicationLogo(it, Modifier.weight(1f)) }
                    }
                }
            }
        }
        items(restApps.size) { index ->
            ApplicationLogo(restApps[index])
        }
    }
}
