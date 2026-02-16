package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeMissionDto(
    @SerialName("title") val title: String,
    @SerialName("context") val context: String,
    @SerialName("company") val company: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("tasks") val tasks: List<String>,
    @SerialName("link") val link: ResumeMissionLinkDto? = null,
    @SerialName("technologies") val skills: List<ResumeMissionSkillDto>,
)
