package fr.aylabs.ayphone.missions.ui.components

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
import androidx.compose.ui.Modifier
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.system.CloseLine
import fr.aylabs.ayphone.missions.ui.states.MissionsFilterState
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ActiveFilterChips(
    filterState: MissionsFilterState,
    onRemoveSkill: (String) -> Unit,
    onRemoveDuration: () -> Unit,
    onRemoveCompany: (String) -> Unit,
    onClearAll: () -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(AySpacings.xs),
        verticalArrangement = Arrangement.spacedBy(AySpacings.xs),
        modifier = Modifier.padding(horizontal = AySpacings.s, vertical = AySpacings.xs),
    ) {
        filterState.selectedSkills.forEach { skill ->
            InputChip(
                selected = true,
                onClick = { onRemoveSkill(skill) },
                label = { Text(skill) },
                trailingIcon = {
                    Icon(
                        imageVector = Remix.System.CloseLine,
                        contentDescription = "Retirer",
                        modifier = Modifier.size(AySizes.iconXs),
                    )
                },
            )
        }
        filterState.durationRange?.let { range ->
            InputChip(
                selected = true,
                onClick = onRemoveDuration,
                label = { Text("${range.first} - ${range.last} mois") },
                trailingIcon = {
                    Icon(
                        imageVector = Remix.System.CloseLine,
                        contentDescription = "Retirer",
                        modifier = Modifier.size(AySizes.iconXs),
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
                        modifier = Modifier.size(AySizes.iconXs),
                    )
                },
            )
        }
        TextButton(onClick = onClearAll) {
            Text("Tout effacer")
        }
    }
}
