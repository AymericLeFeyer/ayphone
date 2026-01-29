package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import fr.aylabs.ayphone.resume.domain.models.Resume

fun ResumeDto.toModel(): Resume {
    return Resume(
        name = this.name,
        role = this.role,
        contacts = this.contacts.toModel(),
        companies = this.companies.map { it.toModel() },
        education = this.educations.map { it.toModel() },
        missions = this.missions.map { it.toModel() }
    )
}