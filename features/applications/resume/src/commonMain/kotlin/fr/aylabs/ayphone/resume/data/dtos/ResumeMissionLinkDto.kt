package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeMissionLinkDto(
    @SerialName("url") val url: String,
    @SerialName("text") val text: String,
)
