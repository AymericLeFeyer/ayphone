package fr.aylabs.ayphone.hobbies.domain.models

data class HobbiesSection(
    val title: String,
    val emoji: String,
    val description: String,
    val links: List<HobbiesLink> = emptyList(),
    val tools: List<HomelabTool> = emptyList(),
)
