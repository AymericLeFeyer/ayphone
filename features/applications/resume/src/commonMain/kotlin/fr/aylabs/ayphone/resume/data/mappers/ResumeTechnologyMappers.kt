package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeMissionTechnologyDto
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionTechnology

fun ResumeMissionTechnologyDto.toModel(): ResumeMissionTechnology {
    return ResumeMissionTechnology(
        name = this.name,
        frequency = this.frequency,
        comments = this.comments
    )
}