package fr.aylabs.ayphone.resume.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.system.CloseLine
import fr.aylabs.ayphone.resume.ui.states.DurationFilter
import fr.aylabs.ayphone.resume.ui.states.ResumeFilterState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ActiveFilterChips(
    filterState: ResumeFilterState,
    onRemoveTechnology: (String) -> Unit,
    onRemoveDuration: (DurationFilter) -> Unit,
    onRemoveCompany: (String) -> Unit,
    onClearAll: () -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        filterState.selectedTechnologies.forEach { tech ->
            InputChip(
                selected = true,
                onClick = { onRemoveTechnology(tech) },
                label = { Text(tech) },
                trailingIcon = {
                    Icon(
                        imageVector = Remix.System.CloseLine,
                        contentDescription = "Retirer",
                        modifier = Modifier.size(16.dp),
                    )
                },
            )
        }
        filterState.selectedDurations.forEach { duration ->
            InputChip(
                selected = true,
                onClick = { onRemoveDuration(duration) },
                label = { Text(duration.label) },
                trailingIcon = {
                    Icon(
                        imageVector = Remix.System.CloseLine,
                        contentDescription = "Retirer",
                        modifier = Modifier.size(16.dp),
                    )
                },
            )
        }
        filterState.selectedCompanies.forEach { company ->
            InputChip(
                selected = true,
                onClick = { onRemoveCompany(company) },
                label = { Text(company) },
                trailingIcon = {
                    Icon(
                        imageVector = Remix.System.CloseLine,
                        contentDescription = "Retirer",
                        modifier = Modifier.size(16.dp),
                    )
                },
            )
        }
        TextButton(onClick = onClearAll) {
            Text("Tout effacer")
        }
    }
}
