package fr.aylabs.ayphone.stack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionSkill
import fr.aylabs.ayphone.resume.domain.models.Skill
import fr.aylabs.ayphone.resume.domain.models.SkillCategory
import fr.aylabs.ayphone.stack.ui.components.SkillChip
import fr.aylabs.ayphone.stack.ui.components.SkillDetailSheet

@Composable
fun StackReadyScreen(
    resume: Resume,
    onSeeRelatedMissions: (String) -> Unit,
) {
    val skillsByCategory: List<Pair<SkillCategory, List<ResumeMissionSkill>>> =
        remember(resume) {
            val skills = resume.missions
                .flatMap { it.skills }
                .groupBy { it.name }
                .map { (name, entries) ->
                    ResumeMissionSkill(
                        name = name,
                        frequency = entries.maxOf { it.frequency },
                        comments = Skill.fromLabel(name)?.description ?: "",
                    )
                }

            skills
                .groupBy { skill ->
                    Skill.fromLabel(skill.name)?.category ?: SkillCategory.TOOLS
                }
                .entries
                .sortedBy { it.key.ordinal }
                .map { (category, items) -> category to items.sortedByDescending { it.frequency } }
        }

    var selectedSkill by remember { mutableStateOf<ResumeMissionSkill?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        skillsByCategory.forEach { (category, skills) ->
            item(
                key = "header_${category.name}",
                span = { GridItemSpan(maxLineSpan) },
            ) {
                Text(
                    text = category.label,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                )
            }
            items(skills, key = { it.name }) { skill ->
                SkillChip(
                    name = skill.name,
                    onClick = { selectedSkill = skill },
                )
            }
        }
    }

    selectedSkill?.let { skill ->
        SkillDetailSheet(
            skillName = skill.name,
            description = Skill.fromLabel(skill.name)?.description ?: "",
            onSeeRelatedMissions = {
                onSeeRelatedMissions(skill.name)
                selectedSkill = null
            },
            onDismiss = { selectedSkill = null },
        )
    }
}
