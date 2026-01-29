package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeDto(
    @SerialName("name") val name: String,
    @SerialName("role") val role: String,
    @SerialName("contacts") val contacts: ResumeContactDto,
    @SerialName("companies") val companies: List<ResumeCompanyDto>,
    @SerialName("education") val educations: List<ResumeEducationDto>,
    @SerialName("missions") val missions: List<ResumeMissionDto>,
)