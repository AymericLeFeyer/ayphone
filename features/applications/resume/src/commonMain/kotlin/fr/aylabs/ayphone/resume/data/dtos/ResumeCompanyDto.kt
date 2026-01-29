package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeCompanyDto(
    @SerialName("company") val company: String,
    @SerialName("position") val position: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("responsibilities") val responsibilities: List<String>,
)
