package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeEducationDto
import fr.aylabs.ayphone.resume.domain.models.ResumeEducation
import kotlinx.datetime.YearMonth

fun ResumeEducationDto.toModel(): ResumeEducation {
    return ResumeEducation(
        institution = this.institution,
        degree = this.degree,
        startDate = YearMonth.parse(this.startDate),
        endDate = runCatching { YearMonth.parse(this.endDate) }.getOrNull(),
    )
}