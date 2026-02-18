package fr.aylabs.ayphone.missions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.aylabs.ayphone.missions.ui.components.ActiveFilterChips
import fr.aylabs.ayphone.missions.ui.components.MissionFilterSheet
import fr.aylabs.ayphone.missions.ui.components.MissionItem
import fr.aylabs.ayphone.missions.ui.components.MissionSearchBar
import fr.aylabs.ayphone.missions.ui.viewmodels.MissionsViewModel
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.dates.monthsBetween

@Composable
fun MissionsListScreen(
    resume: Resume,
    vm: MissionsViewModel,
    onMissionClick: (Int) -> Unit,
    showFilterSheet: Boolean,
    onDismissFilterSheet: () -> Unit,
) {
    val filterState by vm.filterState.collectAsStateWithLifecycle()
    val filteredMissions by vm.filteredMissions.collectAsStateWithLifecycle()
    val allMissions by vm.allMissions.collectAsStateWithLifecycle()

    val allSkills = remember(allMissions) {
        allMissions.flatMap { it.skills }.map { it.name }.distinct().sorted()
    }
    val allCompanies = remember(allMissions) {
        allMissions.map { it.company }.distinct().sorted()
    }
    val allDurationRange = remember(allMissions) {
        val durations = allMissions.map { monthsBetween(it.startDate, it.endDate) }
        (durations.minOrNull() ?: 0)..(durations.maxOrNull() ?: 24)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        MissionSearchBar(
            query = filterState.searchQuery,
            onQueryChange = vm::updateSearchQuery,
        )

        if (filterState.hasActiveFilters()) {
            ActiveFilterChips(
                filterState = filterState,
                onRemoveSkill = vm::removeSkillFilter,
                onRemoveDuration = vm::clearDurationFilter,
                onRemoveCompany = vm::removeCompanyFilter,
                onClearAll = vm::clearFilters,
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(vertical = 4.dp),
        ) {
            items(filteredMissions, key = { it.title + it.startDate.toString() }) { mission ->
                val originalIndex = resume.missions.indexOf(mission)
                MissionItem(
                    mission = mission,
                    onClick = { onMissionClick(originalIndex) },
                )
            }
        }
    }

    if (showFilterSheet) {
        MissionFilterSheet(
            filterState = filterState,
            allSkills = allSkills,
            allCompanies = allCompanies,
            allDurationRange = allDurationRange,
            onToggleSkill = vm::toggleSkill,
            onUpdateDurationRange = vm::updateDurationRange,
            onToggleCompany = vm::toggleCompany,
            onDismiss = onDismissFilterSheet,
        )
    }
}
