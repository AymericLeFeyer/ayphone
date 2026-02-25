package fr.aylabs.ayphone.missions.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.aylabs.ayphone.missions.ui.components.SafeImage
import fr.aylabs.ayphone.missions.ui.components.SkillChip
import fr.aylabs.ayphone.missions.ui.components.SkillDetailSheet
import fr.aylabs.ayphone.missions.ui.states.MissionsState
import fr.aylabs.ayphone.missions.ui.viewmodels.MissionsViewModel
import fr.aylabs.ayphone.resume.domain.models.Company
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionSkill
import fr.aylabs.dates.monthsBetween
import fr.aylabs.design_system.AyColors
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AyDetailScaffold
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MissionDetailScreen(
    missionIndex: Int,
    vm: MissionsViewModel,
    onBackClick: () -> Unit,
    onSeeSkillDetail: (String) -> Unit = {},
    onCompanyClick: (String) -> Unit = {},
) {
    val uiState by vm.state.collectAsStateWithLifecycle()
    val resume = (uiState as? MissionsState.Success)?.data
    val mission = resume?.missions?.getOrNull(missionIndex)
    val uriHandler = LocalUriHandler.current

    var selectedSkill by remember { mutableStateOf<ResumeMissionSkill?>(null) }
    val skillSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    AyDetailScaffold(
        title = mission?.title ?: "",
        onBackClick = onBackClick,
    ) { padding ->
        if (mission != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(AySpacings.l),
                verticalArrangement = Arrangement.spacedBy(AySpacings.m),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onCompanyClick(mission.company) },
                    ) {
                        Company.fromLabel(mission.company)?.let { company ->
                            SafeImage(
                                resourcePath = company.iconPath,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(AySizes.iconM)
                                    .clip(RoundedCornerShape(AyCorners.xs)),
                            )
                            Spacer(Modifier.width(AySpacings.s))
                        }
                        Text(
                            text = mission.company,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Text(
                        text = "${monthsBetween(mission.startDate, mission.endDate)} mois",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Text(
                    text = mission.context,
                    style = MaterialTheme.typography.bodyMedium,
                )

                if (mission.skills.isNotEmpty()) {
                    Text(
                        text = "Compétences",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(AySpacings.xs),
                        verticalArrangement = Arrangement.spacedBy(AySpacings.xs),
                    ) {
                        mission.skills.forEach { skill ->
                            SkillChip(
                                name = skill.name,
                                onClick = { selectedSkill = skill },
                            )
                        }
                    }
                }

                if (mission.tasks.isNotEmpty()) {
                    Text(
                        text = "Tâches réalisées",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                    )
                    Column {
                        mission.tasks.forEach { task ->
                            Text(
                                text = "\u2022 $task",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(
                                    start = AySpacings.xs,
                                    top = AySpacings.xxs
                                ),
                            )
                        }
                    }
                }

                mission.link?.let { link ->
                    TextButton(onClick = { uriHandler.openUri(link.url) }) {
                        Text(
                            text = link.text,
                            style = MaterialTheme.typography.labelMedium,
                            color = AyColors.Primary,
                        )
                    }
                }
            }
        }
    }

    selectedSkill?.let { skill ->
        val dismissSkillSheet = {
            coroutineScope.launch { skillSheetState.hide() }.invokeOnCompletion {
                if (!skillSheetState.isVisible) selectedSkill = null
            }
            Unit
        }
        SkillDetailSheet(
            skillName = skill.name,
            description = skill.comments,
            frequency = skill.frequency,
            sheetState = skillSheetState,
            onSeeSkillDetail = {
                dismissSkillSheet()
                onSeeSkillDetail(skill.name)
            },
            onDismiss = { dismissSkillSheet() },
        )
    }
}
