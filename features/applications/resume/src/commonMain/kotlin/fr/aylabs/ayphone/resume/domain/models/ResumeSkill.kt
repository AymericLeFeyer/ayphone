package fr.aylabs.ayphone.resume.domain.models

data class ResumeSkill(
    val skill: Skill,
    val score: Double,
    val totalMonths: Int,
)
