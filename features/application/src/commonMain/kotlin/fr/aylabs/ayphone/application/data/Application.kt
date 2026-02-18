package fr.aylabs.ayphone.application.data

import androidx.compose.ui.graphics.vector.ImageVector

abstract class Application(
    val title: String,
    val logo: ImageVector? = null,
    // Store metadata — null means not installable via AyShop
    val id: String? = null,
    val developer: String? = null,
    val category: String? = null,
    val shortDescription: String? = null,
    val description: String? = null,
    val features: List<String> = emptyList(),
    val whatsNew: List<String> = emptyList(),
    val rating: Float? = null,
    val reviewCount: String? = null,
    val downloads: String? = null,
    val version: String? = null,
    val size: String? = null,
    val iconEmoji: String? = null,
    val iconColor: Long? = null,
) {
    open fun onClick() {}
}
