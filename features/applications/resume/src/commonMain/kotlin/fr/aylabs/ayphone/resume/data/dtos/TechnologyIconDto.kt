package fr.aylabs.ayphone.resume.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TechnologyIconDto(
    val name: String,
    val icon: String,
    val category: String,
)
