package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeContactDto(
    @SerialName("email") val email: String,
    @SerialName("linkedin") val linkedin: String,
    @SerialName("github") val github: String,
)