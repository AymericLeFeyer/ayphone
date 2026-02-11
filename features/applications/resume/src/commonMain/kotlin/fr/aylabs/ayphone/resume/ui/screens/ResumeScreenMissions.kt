package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.ui.components.MissionItem

@Composable
fun ResumeScreenMissions(resume: Resume) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(vertical = 4.dp)
            .verticalScroll(rememberScrollState())
    ) {

        resume.missions.map { mission ->
            MissionItem(mission)
        }
    }
}