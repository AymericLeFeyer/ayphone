package fr.aylabs.ayphone.sideprojects.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface SideProjectsRoutes {
    @Serializable
    @SerialName("SideProjectsGraph")
    data object Graph : SideProjectsRoutes

    @Serializable
    @SerialName("SideProjects")
    data class Root(val initialSkillFilter: String = "") : SideProjectsRoutes

    @Serializable
    @SerialName("SideProjectDetail")
    data class MissionDetail(val missionIndex: Int) : SideProjectsRoutes
}
