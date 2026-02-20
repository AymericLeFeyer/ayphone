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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.models.ResumeSkill
import fr.aylabs.ayphone.resume.domain.models.SkillCategory
import fr.aylabs.ayphone.stack.ui.components.SkillChip
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@Composable
fun StackReadyScreen(
    resume: Resume,
    grouping: StackGrouping,
    onSkillClick: (String) -> Unit,
) {
    val skillsByCategory: List<Pair<SkillCategory, List<ResumeSkill>>> =
        remember(resume.skills) {
            resume.skills
                .groupBy { it.skill.category }
                .entries
                .sortedBy { it.key.ordinal }
                .map { (category, items) -> category to items.sortedByDescending { it.score } }
        }

    val skillsByDuration: List<Pair<String, List<ResumeSkill>>> =
        remember(resume.skills) {
            val sorted = resume.skills.sortedByDescending { it.totalMonths }
            val thresholds = listOf(
                48 to "4 ans et plus",
                36 to "3 ans et plus",
                24 to "2 ans et plus",
                12 to "1 an et plus",
                0 to "Moins d'1 an",
            )
            val result = mutableListOf<Pair<String, List<ResumeSkill>>>()
            val remaining = sorted.toMutableList()
            for ((minMonths, label) in thresholds) {
                val matching = remaining.filter { it.totalMonths >= minMonths }
                if (matching.isNotEmpty()) {
                    result.add(label to matching)
                    remaining.removeAll(matching)
                }
            }
            result
        }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(AySizes.skillGridCell),
        horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
        verticalArrangement = Arrangement.spacedBy(AySpacings.s),
        modifier = Modifier
            .fillMaxSize()
            .padding(AySpacings.s),
    ) {
        when (grouping) {
            StackGrouping.CATEGORY -> {
                skillsByCategory.forEach { (category, skills) ->
                    item(
                        key = "header_${category.name}",
                        span = { GridItemSpan(maxLineSpan) },
                    ) {
                        Text(
                            text = category.label,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = AySpacings.m, bottom = AySpacings.xs),
                        )
                    }
                    items(skills, key = { it.skill.label }) { skill ->
                        SkillChip(
                            name = skill.skill.label,
                            onClick = { onSkillClick(skill.skill.label) },
                        )
                    }
                }
            }

            StackGrouping.DURATION -> {
                skillsByDuration.forEach { (label, skills) ->
                    item(
                        key = "header_duration_$label",
                        span = { GridItemSpan(maxLineSpan) },
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = AySpacings.m, bottom = AySpacings.xs),
                        )
                    }
                    items(skills, key = { "${label}_${it.skill.label}" }) { skill ->
                        SkillChip(
                            name = skill.skill.label,
                            onClick = { onSkillClick(skill.skill.label) },
                        )
                    }
                }
            }
        }
    }

}
