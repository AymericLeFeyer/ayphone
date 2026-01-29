package fr.aylabs.ayphone.resume.domain.models

import kotlinx.datetime.YearMonth


data class ResumeCompany(
    val name: String,
    val position: String,
    val startDate: YearMonth,
    val endDate: YearMonth?,
    val responsibilities: List<String>,
)
