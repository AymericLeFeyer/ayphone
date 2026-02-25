package fr.aylabs.ayphone.stack.ui.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface StackRoutes {
    @Serializable
    @SerialName("stack-graph")
    data object Graph : StackRoutes

    @Serializable
    @SerialName("stack")
    data object Root : StackRoutes

    @Serializable
    @SerialName("skill-detail")
    data class SkillDetail(val skillLabel: String) : StackRoutes
}
