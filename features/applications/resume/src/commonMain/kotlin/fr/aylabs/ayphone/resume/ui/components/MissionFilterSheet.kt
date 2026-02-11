package fr.aylabs.ayphone.resume.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.ui.states.DurationFilter
import fr.aylabs.ayphone.resume.ui.states.ResumeFilterState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MissionFilterSheet(
    filterState: ResumeFilterState,
    allTechnologies: List<String>,
    allCompanies: List<String>,
    onToggleTechnology: (String) -> Unit,
    onToggleDuration: (DurationFilter) -> Unit,
    onToggleCompany: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Filtres",
                style = MaterialTheme.typography.titleMedium,
            )

            if (allTechnologies.isNotEmpty()) {
                Text(
                    text = "Technologies",
                    style = MaterialTheme.typography.labelLarge,
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    allTechnologies.forEach { tech ->
                        FilterChip(
                            selected = tech in filterState.selectedTechnologies,
                            onClick = { onToggleTechnology(tech) },
                            label = { Text(tech) },
                        )
                    }
                }
            }

            Text(
                text = "Durée",
                style = MaterialTheme.typography.labelLarge,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                DurationFilter.entries.forEach { duration ->
                    FilterChip(
                        selected = duration in filterState.selectedDurations,
                        onClick = { onToggleDuration(duration) },
                        label = { Text(duration.label) },
                    )
                }
            }

            if (allCompanies.isNotEmpty()) {
                Text(
                    text = "Entreprises",
                    style = MaterialTheme.typography.labelLarge,
                )
                Column {
                    allCompanies.forEach { company ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Checkbox(
                                checked = company in filterState.selectedCompanies,
                                onCheckedChange = { onToggleCompany(company) },
                            )
                            Text(
                                text = company,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            }
        }
    }
}
