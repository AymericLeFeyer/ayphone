package fr.aylabs.ayphone.missions.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.missions.ui.states.MissionsFilterState
import fr.aylabs.ayphone.missions.ui.states.MissionsState
import fr.aylabs.ayphone.resume.domain.models.ResumeMission
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.dates.monthsBetween
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class MissionsViewModel(
    private val getResumeUseCase: GetResumeUseCase,
    private val missionPredicate: (ResumeMission) -> Boolean = { !it.isSideProject },
) : ViewModel() {

    private val mutableState = MutableStateFlow<MissionsState>(MissionsState.Initial)
    val state: StateFlow<MissionsState> = mutableState

    private val mutableFilterState = MutableStateFlow(MissionsFilterState())
    val filterState: StateFlow<MissionsFilterState> = mutableFilterState

    val allMissions: StateFlow<List<ResumeMission>> = mutableState.map { state ->
        val resume = (state as? MissionsState.Success)?.data ?: return@map emptyList()
        resume.missions.filter(missionPredicate)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredMissions: StateFlow<List<ResumeMission>> = combine(
        mutableState,
        mutableFilterState,
    ) { state, filters ->
        val resume = (state as? MissionsState.Success)?.data ?: return@combine emptyList()
        resume.missions.filter { mission ->
            missionPredicate(mission) &&
                matchesSearch(mission, filters.searchQuery) &&
                matchesSkills(mission, filters.selectedSkills) &&
                matchesDuration(mission, filters.durationRange) &&
                matchesCompanies(mission, filters.selectedCompanies)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch { loadData() }
    }

    suspend fun loadData() {
        runCatching {
            mutableState.value = MissionsState.Loading
            getResumeUseCase()
        }.onSuccess {
            mutableState.value = MissionsState.Success(it)
        }.onFailure {
            mutableState.value = MissionsState.Error(it)
        }
    }

    fun updateSearchQuery(query: String) {
        mutableFilterState.value = mutableFilterState.value.copy(searchQuery = query)
    }

    fun toggleSkill(skill: String) {
        val current = mutableFilterState.value.selectedSkills
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedSkills = if (skill in current) current - skill else current + skill,
        )
    }

    fun updateDurationRange(range: IntRange?) {
        mutableFilterState.value = mutableFilterState.value.copy(durationRange = range)
    }

    fun clearDurationFilter() {
        mutableFilterState.value = mutableFilterState.value.copy(durationRange = null)
    }

    fun toggleCompany(company: String) {
        val current = mutableFilterState.value.selectedCompanies
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedCompanies = if (company in current) current - company else current + company,
        )
    }

    fun clearFilters() {
        mutableFilterState.value = MissionsFilterState()
    }

    fun removeSkillFilter(skill: String) {
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedSkills = mutableFilterState.value.selectedSkills - skill,
        )
    }

    fun setInitialSkillFilter(skillName: String) {
        if (skillName.isNotBlank()) {
            mutableFilterState.value = MissionsFilterState(selectedSkills = setOf(skillName))
        }
    }

    fun removeCompanyFilter(company: String) {
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedCompanies = mutableFilterState.value.selectedCompanies - company,
        )
    }

    private fun matchesSearch(mission: ResumeMission, query: String): Boolean {
        if (query.isBlank()) return true
        val lowerQuery = query.lowercase()
        return mission.title.lowercase().contains(lowerQuery) ||
            mission.company.lowercase().contains(lowerQuery) ||
            mission.context.lowercase().contains(lowerQuery) ||
            mission.skills.any { it.name.lowercase().contains(lowerQuery) }
    }

    private fun matchesSkills(mission: ResumeMission, skills: Set<String>): Boolean {
        if (skills.isEmpty()) return true
        return mission.skills.any { it.name in skills }
    }

    private fun matchesDuration(mission: ResumeMission, durationRange: IntRange?): Boolean {
        if (durationRange == null) return true
        val months = monthsBetween(mission.startDate, mission.endDate)
        return months in durationRange
    }

    private fun matchesCompanies(mission: ResumeMission, companies: Set<String>): Boolean {
        if (companies.isEmpty()) return true
        return mission.company in companies
    }
}
