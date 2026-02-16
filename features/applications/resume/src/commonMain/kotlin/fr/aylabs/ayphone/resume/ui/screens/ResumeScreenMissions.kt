package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.ui.components.ActiveFilterChips
import fr.aylabs.ayphone.resume.ui.components.MissionFilterSheet
import fr.aylabs.ayphone.resume.ui.components.MissionItem
import fr.aylabs.ayphone.resume.ui.components.MissionSearchBar
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import fr.aylabs.dates.monthsBetween

@Composable
fun ResumeScreenMissions(
    resume: Resume,
    vm: ResumeViewModel,
    onMissionClick: (Int) -> Unit,
) {
    val filterState by vm.filterState.collectAsStateWithLifecycle()
    val filteredMissions by vm.filteredMissions.collectAsStateWithLifecycle()
    var showFilterSheet by remember { mutableStateOf(false) }

    val allSkills = remember(resume) {
        resume.missions.flatMap { it.skills }.map { it.name }.distinct().sorted()
    }
    val allCompanies = remember(resume) {
        resume.missions.map { it.company }.distinct().sorted()
    }
    val allDurationRange = remember(resume) {
        val durations = resume.missions.map { monthsBetween(it.startDate, it.endDate) }
        (durations.minOrNull() ?: 0)..(durations.maxOrNull() ?: 24)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        MissionSearchBar(
            query = filterState.searchQuery,
            onQueryChange = vm::updateSearchQuery,
            hasActiveFilters = filterState.hasActiveFilters(),
            onFilterClick = { showFilterSheet = true },
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
            onDismiss = { showFilterSheet = false },
        )
    }
}
