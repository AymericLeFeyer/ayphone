package fr.aylabs.ayphone.search.domain.usecases

import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.search.domain.models.SearchResult

class SearchUseCase(private val getResumeUseCase: GetResumeUseCase) {

    suspend operator fun invoke(query: String): List<SearchResult> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        val resume = getResumeUseCase()
        val results = mutableListOf<SearchResult>()

        // Apps — match by title
        AyApp.entries.forEach { app ->
            if (app.title.lowercase().contains(q)) {
                results.add(SearchResult.App(app))
            }
        }

        // Missions (non-side-project) — match by title or company
        val regularMissions = resume.missions.filter { !it.isSideProject }
        regularMissions.forEachIndexed { index, mission ->
            if (mission.title.lowercase().contains(q) ||
                mission.company.lowercase().contains(q)
            ) {
                results.add(SearchResult.Mission(index, mission.title, mission.company))
            }
        }

        // Skills — match by label
        resume.skills.forEach { resumeSkill ->
            if (resumeSkill.skill.label.lowercase().contains(q)) {
                results.add(SearchResult.TechSkill(resumeSkill.skill))
            }
        }

        // Clients — match by name
        resume.companies.forEach { company ->
            if (company.name.lowercase().contains(q)) {
                results.add(SearchResult.Client(company.name))
            }
        }

        // Side projects — match by title
        val sideProjects = resume.missions.filter { it.isSideProject }
        sideProjects.forEachIndexed { index, mission ->
            if (mission.title.lowercase().contains(q)) {
                results.add(SearchResult.SideProject(index, mission.title))
            }
        }

        return results.take(10)
    }
}
