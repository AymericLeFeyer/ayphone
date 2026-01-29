package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeMissionLinkDto
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionLink

fun ResumeMissionLinkDto.toModel(): ResumeMissionLink {
    return ResumeMissionLink(
        url = this.url,
        text = this.text
    )
}