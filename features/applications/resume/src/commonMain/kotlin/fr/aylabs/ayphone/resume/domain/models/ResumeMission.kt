package fr.aylabs.ayphone.resume.domain.models

import kotlinx.datetime.YearMonth

data class ResumeMission(
    val title: String,
    val context: String,
    val company: String,
    val startDate: YearMonth,
    val endDate: YearMonth?,
    val link: ResumeMissionLink?,
    val technologies: List<ResumeMissionTechnology>,
    val tasks: List<String>
)
