package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeCompanyDto
import fr.aylabs.ayphone.resume.domain.models.ResumeCompany
import kotlinx.datetime.YearMonth

fun ResumeCompanyDto.toModel(): ResumeCompany {
    return ResumeCompany(
        name = this.company,
        position = this.position,
        startDate = YearMonth.parse(this.startDate),
        endDate = runCatching { YearMonth.parse(this.endDate) }.getOrNull(),
        responsibilities = this.responsibilities
    )
}