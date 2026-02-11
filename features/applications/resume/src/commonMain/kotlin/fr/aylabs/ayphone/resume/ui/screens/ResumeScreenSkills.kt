package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.ui.components.MissionItem

@Composable
fun ResumeScreenSkills(resume: Resume) {
    Column {
        resume.missions.map { mission ->
            MissionItem(mission)
        }
    }
}