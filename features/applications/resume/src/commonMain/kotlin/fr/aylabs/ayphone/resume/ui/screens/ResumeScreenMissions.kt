package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.ui.components.MissionItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun ResumeScreenMissions(resume: Resume) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(vertical = 4.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(Res.drawable.android),
            contentDescription = "Android logo",
            modifier = Modifier.width(64.dp).height(64.dp),
            contentScale = ContentScale.Crop,
        )
        resume.missions.map { mission ->
            MissionItem(mission)
        }
    }
}