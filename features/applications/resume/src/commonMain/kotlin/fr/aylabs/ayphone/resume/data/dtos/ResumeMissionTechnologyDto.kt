package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeMissionTechnologyDto(
    @SerialName("name") val name: String,
    @SerialName("frequency") val frequency: Double,
    @SerialName("comments") val comments: String,

    )
