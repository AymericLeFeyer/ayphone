package fr.aylabs.ayphone.resume.domain.models

import kotlinx.datetime.YearMonth

data class ResumeEducation(
    val institution: String,
    val degree: String,
    val startDate: YearMonth,
    val endDate: YearMonth?,
)
