package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CompanyIconDto(
    val name: String,
    val icon: String,
)
