package fr.aylabs.ayphone.sideprojects.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface SideProjectsRoutes {
    @Serializable
    @SerialName("side-projects-graph")
    data object Graph : SideProjectsRoutes

    @Serializable
    @SerialName("side-projects")
    data class Root(val initialSkillFilter: String = "") : SideProjectsRoutes

    @Serializable
    @SerialName("side-project-detail")
    data class MissionDetail(val missionIndex: Int) : SideProjectsRoutes
}
