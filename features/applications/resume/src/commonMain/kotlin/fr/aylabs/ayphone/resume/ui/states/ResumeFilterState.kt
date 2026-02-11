package fr.aylabs.ayphone.resume.ui.states

data class ResumeFilterState(
    val searchQuery: String = "",
    val selectedTechnologies: Set<String> = emptySet(),
    val selectedDurations: Set<DurationFilter> = emptySet(),
    val selectedCompanies: Set<String> = emptySet(),
) {
    fun hasActiveFilters(): Boolean =
        selectedTechnologies.isNotEmpty() || selectedDurations.isNotEmpty() || selectedCompanies.isNotEmpty()
}

enum class DurationFilter(val label: String) {
    LESS_THAN_6("< 6 mois"),
    BETWEEN_6_AND_12("6-12 mois"),
    MORE_THAN_12("> 1 an"),
}
