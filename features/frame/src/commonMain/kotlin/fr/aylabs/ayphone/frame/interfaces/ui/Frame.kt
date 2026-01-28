package fr.aylabs.ayphone.frame.interfaces.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.application.data.Application
import fr.aylabs.ayphone.resume.data.ResumeApp

@Composable
fun Frame() {
    val apps = listOf<Application>(
        ResumeApp(),
        ResumeApp(),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(apps.size) { index ->
            val app = apps[index]
            app.ApplicationLogo()
        }
    }

}