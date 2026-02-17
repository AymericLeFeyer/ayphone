package fr.aylabs.ayphone.resume.domain.models

data class Resume(
    val name: String,
    val role: String,
    val contacts: ResumeContact,
    val companies: List<ResumeCompany>,
    val missions: List<ResumeMission>,
    val education: List<ResumeEducation>,
    val skills: List<ResumeSkill> = emptyList(),
)