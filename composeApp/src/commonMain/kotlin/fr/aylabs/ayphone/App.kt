package fr.aylabs.ayphone

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import fr.aylabs.ayphone.frame.interfaces.ui.Frame

@Composable
fun App() {
    MaterialTheme {
        Frame()
    }
}