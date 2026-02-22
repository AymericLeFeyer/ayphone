package fr.aylabs.ayphone.hobbies.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class HomelabTool(
    val name: String,
    val description: String,
    val logo: ImageVector,
    val action: HobbiesAction,
)
