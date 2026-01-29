package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeContactDto
import fr.aylabs.ayphone.resume.domain.models.ResumeContact

fun ResumeContactDto.toModel(): ResumeContact {
    return ResumeContact(
        email = this.email,
        phone = this.phone,
        linkedin = this.linkedin,
        github = this.github
    )
}