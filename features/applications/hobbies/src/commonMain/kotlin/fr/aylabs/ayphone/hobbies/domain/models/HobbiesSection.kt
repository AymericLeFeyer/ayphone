package fr.aylabs.ayphone.hobbies.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class HobbiesSection(
    val title: String,
    val icon: ImageVector,
    val description: String,
    val links: List<HobbiesLink> = emptyList(),
    val tools: List<HomelabTool> = emptyList(),
)
