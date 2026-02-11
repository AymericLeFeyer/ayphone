package fr.aylabs.ayphone.resume.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionTechnology

@Composable
fun SkillItem(skill: ResumeMissionTechnology) {
    Row {
        Text(skill.toString())
    }
}