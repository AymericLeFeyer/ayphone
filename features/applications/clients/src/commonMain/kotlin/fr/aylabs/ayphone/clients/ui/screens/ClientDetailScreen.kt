package fr.aylabs.ayphone.clients.ui.screens

import AyCorners
import AySizes
import AySpacings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.aylabs.ayphone.clients.ui.components.SafeImage
import fr.aylabs.ayphone.clients.ui.components.SkillChip
import fr.aylabs.ayphone.clients.ui.states.ClientsState
import fr.aylabs.ayphone.clients.ui.viewmodels.ClientsViewModel
import fr.aylabs.ayphone.resume.domain.models.Company
import fr.aylabs.dates.formatYearMonth
import fr.aylabs.dates.monthsBetween
import fr.aylabs.design_system.AyDetailScaffold

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ClientDetailScreen(
    companyName: String,
    vm: ClientsViewModel,
    onBackClick: () -> Unit,
    onSeeSkillDetail: (String) -> Unit = {},
    onSeeMission: (Int) -> Unit = {},
) {
    val uiState by vm.state.collectAsStateWithLifecycle()
    val resume = (uiState as? ClientsState.Success)?.data
    val missions = resume?.missions
        ?.withIndex()
        ?.filter { it.value.company == companyName }
        ?.toList()
        ?: emptyList()
    val company = resume?.companies?.find { it.name == companyName }
    val skillNames = missions
        .flatMap { it.value.skills }
        .map { it.name }
        .distinct()

    val startDate = missions.minOfOrNull { it.value.startDate }
    val endDate = if (missions.any { it.value.endDate == null }) null
    else missions.maxOfOrNull { it.value.endDate!! }
    val totalMonths = missions.sumOf { monthsBetween(it.value.startDate, it.value.endDate) }

    AyDetailScaffold(
        title = companyName,
        onBackClick = onBackClick,
    ) { padding ->
        if (missions.isNotEmpty() && startDate != null) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(AySpacings.l),
                verticalArrangement = Arrangement.spacedBy(AySpacings.l),
            ) {
                // Header
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(AyCorners.m))
                        .padding(AySpacings.l),
                ) {
                    Company.fromLabel(companyName)?.let { c ->
                        SafeImage(
                            resourcePath = c.iconPath,
                            contentDescription = null,
                            modifier = Modifier
                                .size(AySizes.iconXxxl)
                                .clip(RoundedCornerShape(AyCorners.s)),
                        )
                    }
                    Text(
                        text = companyName,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = AySpacings.s),
                    )
                    company?.position?.let { position ->
                        Text(
                            text = position,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Text(
                        text = "${formatYearMonth(startDate)} - ${endDate?.let { formatYearMonth(it) } ?: "Présent"}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "$totalMonths mois",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = AySpacings.xs),
                    )
                }

                // Missions section
                Text(
                    text = "Missions",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                missions.forEach { (index, mission) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(AyCorners.s))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .clickable { onSeeMission(index) }
                            .padding(AySpacings.m),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = mission.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = "${monthsBetween(mission.startDate, mission.endDate)} mois",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Text(
                            text = mission.context,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = AySpacings.xs),
                        )
                    }
                }

                // Skills section
                if (skillNames.isNotEmpty()) {
                    Text(
                        text = "Compétences",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(AySpacings.xs),
                        verticalArrangement = Arrangement.spacedBy(AySpacings.xs),
                    ) {
                        skillNames.forEach { skillName ->
                            SkillChip(
                                name = skillName,
                                onClick = { onSeeSkillDetail(skillName) },
                            )
                        }
                    }
                }
            }
        }
    }
}
