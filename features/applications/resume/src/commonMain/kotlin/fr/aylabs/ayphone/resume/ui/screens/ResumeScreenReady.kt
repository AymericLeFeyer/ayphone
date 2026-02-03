package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import fr.aylabs.ayphone.resume.domain.models.Resume

@Composable
fun ResumeScreenReady(
    resume: Resume,
) {
    Text(resume.name)
}