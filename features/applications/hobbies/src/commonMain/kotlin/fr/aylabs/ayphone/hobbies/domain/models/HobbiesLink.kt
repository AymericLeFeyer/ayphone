package fr.aylabs.ayphone.hobbies.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class HobbiesLink(
    val icon: ImageVector,
    val label: String,
    val action: HobbiesAction,
)
