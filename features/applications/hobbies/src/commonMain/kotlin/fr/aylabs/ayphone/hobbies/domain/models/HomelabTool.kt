package fr.aylabs.ayphone.hobbies.domain.models

data class HomelabTool(
    val name: String,
    val description: String,
    val logoUrl: String,
    val action: HobbiesAction,
)
