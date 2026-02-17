package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.models.ResumeMission
import fr.aylabs.ayphone.resume.domain.models.ResumeSkill
import fr.aylabs.ayphone.resume.domain.models.Skill
import fr.aylabs.dates.monthsBetween

fun ResumeDto.toModel(): Resume {
    val missions = this.missions.map { it.toModel() }
    return Resume(
        name = this.name,
        role = this.role,
        contacts = this.contacts.toModel(),
        companies = this.companies.map { it.toModel() },
        education = this.educations.map { it.toModel() },
        missions = missions,
        skills = aggregateSkills(missions),
    )
}

private fun aggregateSkills(missions: List<ResumeMission>): List<ResumeSkill> {
    data class SkillAccumulator(
        var score: Double = 0.0,
        var totalMonths: Int = 0,
    )

    val map = mutableMapOf<Skill, SkillAccumulator>()

    for (mission in missions) {
        val months = monthsBetween(mission.startDate, mission.endDate).coerceAtLeast(1)

        for (missionSkill in mission.skills) {
            val skill = missionSkill.skill ?: continue
            val acc = map.getOrPut(skill) { SkillAccumulator() }
            acc.score += missionSkill.frequency * months
            acc.totalMonths += months
        }
    }

    return map.map { (skill, acc) ->
        ResumeSkill(
            skill = skill,
            score = acc.score,
            totalMonths = acc.totalMonths,
        )
    }
}