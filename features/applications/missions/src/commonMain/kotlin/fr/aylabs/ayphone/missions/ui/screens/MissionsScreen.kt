package fr.aylabs.ayphone.missions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.system.ErrorWarningLine
import com.woowla.compose.icon.collections.remix.remix.system.FilterLine
import fr.aylabs.ayphone.missions.ui.components.MissionFilterSheet
import fr.aylabs.ayphone.missions.ui.states.MissionsState
import fr.aylabs.ayphone.missions.ui.viewmodels.MissionsViewModel
import fr.aylabs.dates.monthsBetween
import fr.aylabs.design_system.AyAppScaffold
import fr.aylabs.design_system.AySpacings
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissionsScreen(
    onBackClick: () -> Unit,
    onMissionClick: (Int) -> Unit,
    vm: MissionsViewModel,
    containerColor: Color,
    title: String = "Missions",
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by vm.state.collectAsStateWithLifecycle()
    val filterState by vm.filterState.collectAsStateWithLifecycle()
    val allMissions by vm.allMissions.collectAsStateWithLifecycle()
    var showFilterSheet by remember { mutableStateOf(false) }

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

    AyAppScaffold(
        title = title,
        containerColor = containerColor,
        onBackClick = onBackClick,
        actions = {
            IconButton(onClick = { showFilterSheet = true }) {
                Icon(
                    imageVector = Remix.System.FilterLine,
                    contentDescription = "Filtrer",
                )
            }
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (val state = uiState) {
                is MissionsState.Initial,
                is MissionsState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is MissionsState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            imageVector = Remix.System.ErrorWarningLine,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                        )
                        Text(
                            text = "Une erreur est survenue",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = AySpacings.s),
                        )
                        Button(
                            onClick = { coroutineScope.launch { vm.loadData() } },
                            modifier = Modifier.padding(top = AySpacings.l),
                        ) {
                            Text("Réessayer")
                        }
                    }
                }

                is MissionsState.Success -> {
                    MissionsListScreen(
                        resume = state.data,
                        vm = vm,
                        onMissionClick = onMissionClick,
                    )
                }
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
