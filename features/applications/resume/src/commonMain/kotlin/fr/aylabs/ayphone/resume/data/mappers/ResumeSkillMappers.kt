package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeMissionSkillDto
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionSkill
import fr.aylabs.ayphone.resume.domain.models.Skill

fun ResumeMissionSkillDto.toModel(): ResumeMissionSkill {
    return ResumeMissionSkill(
        name = this.name,
        frequency = this.frequency,
        comments = this.comments,
        skill = Skill.fromLabel(this.name),
    )
}
