package fr.aylabs.ayphone.application.data

import androidx.compose.ui.graphics.vector.ImageVector

abstract class Application(
    val title: String,
    val logo: ImageVector? = null,
) {
    abstract fun onClick(): Unit
}