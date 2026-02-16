package fr.aylabs.ayphone.resume.ui.states

data class ResumeFilterState(
    val searchQuery: String = "",
    val selectedSkills: Set<String> = emptySet(),
    val durationRange: IntRange? = null,
    val selectedCompanies: Set<String> = emptySet(),
) {
    fun hasActiveFilters(): Boolean =
        selectedSkills.isNotEmpty() || durationRange != null || selectedCompanies.isNotEmpty()
}
