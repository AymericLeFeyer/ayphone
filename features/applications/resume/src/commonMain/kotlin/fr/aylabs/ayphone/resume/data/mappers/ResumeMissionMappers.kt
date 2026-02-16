package fr.aylabs.ayphone.resume.data.mappers

import fr.aylabs.ayphone.resume.data.dtos.ResumeMissionDto
import fr.aylabs.ayphone.resume.domain.models.ResumeMission
import kotlinx.datetime.YearMonth

fun ResumeMissionDto.toModel(): ResumeMission {
    return ResumeMission(
        title = this.title,
        context = this.context,
        company = this.company,
        startDate = YearMonth.parse(this.startDate),
        endDate = runCatching { YearMonth.parse(this.endDate) }.getOrNull(),
        link = this.link?.toModel(),
        skills = this.skills.map { it.toModel() },
        tasks = this.tasks
    )
}
