package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionTechnology
import fr.aylabs.ayphone.resume.ui.components.SkillChip
import fr.aylabs.ayphone.resume.ui.components.TechnologyDetailSheet
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel

@Composable
fun ResumeScreenSkills(
    resume: Resume,
    vm: ResumeViewModel,
) {
    val skills = remember(resume) {
        resume.missions
            .flatMap { it.technologies }
            .groupBy { it.name }
            .map { (name, techs) ->
                ResumeMissionTechnology(
                    name = name,
                    frequency = techs.maxOf { it.frequency },
                    comments = techs.first().comments,
                )
            }
            .sortedByDescending { it.frequency }
    }

    var selectedTech by remember { mutableStateOf<ResumeMissionTechnology?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        items(skills, key = { it.name }) { skill ->
            SkillChip(
                name = skill.name,
                onClick = { selectedTech = skill },
            )
        }
    }

    selectedTech?.let { tech ->
        TechnologyDetailSheet(
            technology = tech,
            onSeeRelatedMissions = {
                vm.navigateToMissionsWithTechFilter(tech.name)
                selectedTech = null
            },
            onDismiss = { selectedTech = null },
        )
    }
}
