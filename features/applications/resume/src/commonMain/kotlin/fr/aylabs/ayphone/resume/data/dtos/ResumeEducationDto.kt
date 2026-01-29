package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeEducationDto(
    @SerialName("institution") val institution: String,
    @SerialName("degree") val degree: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
)
