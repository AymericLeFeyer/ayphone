package fr.aylabs.ayphone.frame.interfaces.ui

import ApplicationLogo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.resume.data.ResumeApp

@Composable
fun Frame(navController: NavHostController) {
    val apps = listOf<Application>(
        ResumeApp(navController),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(apps.size) { index ->
            val app = apps[index]
            ApplicationLogo(app)
        }
    }

}