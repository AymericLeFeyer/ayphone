package fr.aylabs.ayphone.resume.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ResumeRoutes {
    @Serializable
    @SerialName("ResumeGraph")
    data object Graph : ResumeRoutes

    @Serializable
    @SerialName("Resume")
    data object Root : ResumeRoutes

    @Serializable
    @SerialName("ResumeMissionDetail")
    data class MissionDetail(val missionIndex: Int) : ResumeRoutes
}
