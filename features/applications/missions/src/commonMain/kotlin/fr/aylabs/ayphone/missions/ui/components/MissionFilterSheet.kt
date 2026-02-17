package fr.aylabs.ayphone.missions.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowDownSLine
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowUpSLine
import fr.aylabs.ayphone.missions.ui.states.MissionsFilterState
import fr.aylabs.ayphone.resume.domain.models.Company
import fr.aylabs.ayphone.resume.domain.models.Skill
import fr.aylabs.ayphone.resume.domain.models.SkillCategory
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MissionFilterSheet(
    filterState: MissionsFilterState,
    allSkills: List<String>,
    allCompanies: List<String>,
    allDurationRange: IntRange,
    onToggleSkill: (String) -> Unit,
    onUpdateDurationRange: (IntRange?) -> Unit,
    onToggleCompany: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Filtres",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )

            if (allSkills.isNotEmpty()) {
                var skillsExpanded by remember { mutableStateOf(false) }
                val skillsByCategory: List<Pair<SkillCategory, List<String>>> =
                    remember(allSkills) {
                        allSkills
                            .groupBy { name ->
                                Skill.fromLabel(name)?.category ?: SkillCategory.TOOLS
                            }
                            .entries
                            .sortedBy { it.key.ordinal }
                            .map { (category, skills) -> category to skills }
                    }

                CollapsibleHeader(
                    title = "Compétences",
                    expanded = skillsExpanded,
                    onToggle = { skillsExpanded = !skillsExpanded },
                )

                AnimatedVisibility(visible = skillsExpanded) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        skillsByCategory.forEach { (category, skills) ->
                            Text(
                                text = category.label,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                skills.forEach { skillName ->
                                    FilterChip(
                                        selected = skillName in filterState.selectedSkills,
                                        onClick = { onToggleSkill(skillName) },
                                        label = { Text(skillName) },
                                        leadingIcon = {
                                            Skill.fromLabel(skillName)?.let {
                                                SafeImage(
                                                    resourcePath = it.iconPath,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(18.dp),
                                                )
                                            }
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (allDurationRange.first < allDurationRange.last) {
                var durationExpanded by remember { mutableStateOf(false) }

                CollapsibleHeader(
                    title = "Durée",
                    expanded = durationExpanded,
                    onToggle = { durationExpanded = !durationExpanded },
                )

                AnimatedVisibility(visible = durationExpanded) {
                    Column {
                        val currentStart =
                            (filterState.durationRange?.first ?: allDurationRange.first).toFloat()
                        val currentEnd =
                            (filterState.durationRange?.last ?: allDurationRange.last).toFloat()
                        var sliderRange by remember(filterState.durationRange, allDurationRange) {
                            mutableStateOf(currentStart..currentEnd)
                        }

                        val startMonths = sliderRange.start.roundToInt()
                        val endMonths = sliderRange.endInclusive.roundToInt()

                        Text(
                            text = "$startMonths - $endMonths mois",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        RangeSlider(
                            value = sliderRange,
                            onValueChange = { sliderRange = it },
                            onValueChangeFinished = {
                                val start = sliderRange.start.roundToInt()
                                val end = sliderRange.endInclusive.roundToInt()
                                if (start == allDurationRange.first && end == allDurationRange.last) {
                                    onUpdateDurationRange(null)
                                } else {
                                    onUpdateDurationRange(start..end)
                                }
                            },
                            valueRange = allDurationRange.first.toFloat()..allDurationRange.last.toFloat(),
                            steps = allDurationRange.last - allDurationRange.first - 1,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }

            if (allCompanies.isNotEmpty()) {
                var companiesExpanded by remember { mutableStateOf(false) }

                CollapsibleHeader(
                    title = "Entreprises",
                    expanded = companiesExpanded,
                    onToggle = { companiesExpanded = !companiesExpanded },
                )

                AnimatedVisibility(visible = companiesExpanded) {
                    Column {
                        allCompanies.forEach { companyName ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Checkbox(
                                    checked = companyName in filterState.selectedCompanies,
                                    onCheckedChange = { onToggleCompany(companyName) },
                                )
                                Company.fromLabel(companyName)?.let { company ->
                                    SafeImage(
                                        resourcePath = company.iconPath,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(RoundedCornerShape(4.dp)),
                                    )
                                    Spacer(Modifier.width(8.dp))
                                }
                                Text(
                                    text = companyName,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = onDismiss,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Text("Valider")
        }
    }
}

@Composable
private fun CollapsibleHeader(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(vertical = 8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = if (expanded) Remix.Arrows.ArrowUpSLine else Remix.Arrows.ArrowDownSLine,
            contentDescription = if (expanded) "Réduire" else "Développer",
            modifier = Modifier.size(24.dp),
        )
    }
}
