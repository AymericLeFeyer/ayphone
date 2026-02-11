package fr.aylabs.ayphone.resume.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.resume.domain.models.ResumeMission
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.resume.ui.states.DurationFilter
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
                matchesTechnologies(mission, filters.selectedTechnologies) &&
                matchesDuration(mission, filters.selectedDurations) &&
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

    fun toggleTechnology(tech: String) {
        val current = mutableFilterState.value.selectedTechnologies
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedTechnologies = if (tech in current) current - tech else current + tech,
        )
    }

    fun toggleDuration(duration: DurationFilter) {
        val current = mutableFilterState.value.selectedDurations
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedDurations = if (duration in current) current - duration else current + duration,
        )
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

    fun removeTechnologyFilter(tech: String) {
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedTechnologies = mutableFilterState.value.selectedTechnologies - tech,
        )
    }

    fun removeDurationFilter(duration: DurationFilter) {
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedDurations = mutableFilterState.value.selectedDurations - duration,
        )
    }

    fun removeCompanyFilter(company: String) {
        mutableFilterState.value = mutableFilterState.value.copy(
            selectedCompanies = mutableFilterState.value.selectedCompanies - company,
        )
    }

    fun navigateToMissionsWithTechFilter(techName: String) {
        mutableFilterState.value = ResumeFilterState(selectedTechnologies = setOf(techName))
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
            mission.technologies.any { it.name.lowercase().contains(lowerQuery) }
    }

    private fun matchesTechnologies(mission: ResumeMission, technologies: Set<String>): Boolean {
        if (technologies.isEmpty()) return true
        return mission.technologies.any { it.name in technologies }
    }

    private fun matchesDuration(mission: ResumeMission, durations: Set<DurationFilter>): Boolean {
        if (durations.isEmpty()) return true
        val months = monthsBetween(mission.startDate, mission.endDate)
        return durations.any { filter ->
            when (filter) {
                DurationFilter.LESS_THAN_6 -> months < 6
                DurationFilter.BETWEEN_6_AND_12 -> months in 6..12
                DurationFilter.MORE_THAN_12 -> months > 12
            }
        }
    }

    private fun matchesCompanies(mission: ResumeMission, companies: Set<String>): Boolean {
        if (companies.isEmpty()) return true
        return mission.company in companies
    }
}
