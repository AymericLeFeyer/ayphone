package fr.aylabs.ayphone.widget

import androidx.compose.runtime.Composable

abstract class Widget(val size: Int = 2) {
    @Composable
    abstract fun Content()
}
