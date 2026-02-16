package fr.aylabs.ayphone.resume.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.resume.domain.models.ResumeMission
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.resume.ui.states.ResumeFilterState
import fr.aylabs.ayphone.resume.ui.states.ResumeState
import fr.aylabs.dates.monthsBetween
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ResumeViewModel(
    private val getResumeUseCase: GetResumeUseCase,
) : ViewModel() {

    private val mutableState = MutableStateFlow<ResumeState>(ResumeState.Initial)
    val state: StateFlow<ResumeState> = mutableState

    private val mutableFilterState = MutableStateFlow(ResumeFilterState())
    val filterState: StateFlow<ResumeFilterState> = mutableFilterState

    private val mutableRequestedTabIndex = MutableStateFlow<Int?>(null)
    val requestedTabIndex: StateFlow<Int?> = mutableRequestedTabIndex

    val filteredMissions: StateFlow<List<ResumeMission>> = combine(
        mutableState,
        mutableFilterState,
    ) { state, filters ->
        val resume = (state as? ResumeState.Success)?.data ?: return@combine emptyList()
        resume.missions.filter { mission ->
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
            mutableState.value = ResumeState.Loading
            getResumeUseCase()
        }.onSuccess {
            mutableState.value = ResumeState.Success(it)
        }.onFailure {
            mutableState.value = ResumeState.Error(it)
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
        mutableFilterState.value = ResumeFilterState()
    }

    fun removeSkillFilter(skill: String) {
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedSkills = mutableFilterState.value.selectedSkills - skill,
        )
    }

    fun removeCompanyFilter(company: String) {
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedCompanies = mutableFilterState.value.selectedCompanies - company,
        )
    }

    fun navigateToMissionsWithSkillFilter(skillName: String) {
        mutableFilterState.value = ResumeFilterState(selectedSkills = setOf(skillName))
        mutableRequestedTabIndex.value = 0
    }

    fun consumeTabRequest() {
        mutableRequestedTabIndex.value = null
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
