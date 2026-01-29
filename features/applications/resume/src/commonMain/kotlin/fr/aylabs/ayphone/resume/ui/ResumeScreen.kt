package fr.aylabs.ayphone.resume.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ResumeScreen(onBackClick: () -> Unit) {
    Text("I'm the resume screen !")
    Button(onClick = onBackClick) {
        Text("Go back")
    }
}