package fr.aylabs.ayphone.missions.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface MissionsRoutes {
    @Serializable
    @SerialName("missions-graph")
    data object Graph : MissionsRoutes

    @Serializable
    @SerialName("missions")
    data class Root(val initialSkillFilter: String = "") : MissionsRoutes

    @Serializable
    @SerialName("mission-detail")
    data class MissionDetail(val missionIndex: Int) : MissionsRoutes
}
