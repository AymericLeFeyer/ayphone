package fr.aylabs.ayphone.missions.ui.states

data class MissionsFilterState(
    val searchQuery: String = "",
    val selectedSkills: Set<String> = emptySet(),
    val durationRange: IntRange? = null,
    val selectedCompanies: Set<String> = emptySet(),
) {
    fun hasActiveFilters(): Boolean =
        selectedSkills.isNotEmpty() || durationRange != null || selectedCompanies.isNotEmpty()
}
