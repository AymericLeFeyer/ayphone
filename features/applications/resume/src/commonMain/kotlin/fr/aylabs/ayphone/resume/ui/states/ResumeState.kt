package fr.aylabs.ayphone.resume.ui.states

import fr.aylabs.ayphone.resume.domain.models.Resume

sealed class ResumeState {
    data object Initial : ResumeState()
    data object Loading : ResumeState()
    data class Success(val data: Resume) : ResumeState()
    data class Error(val error: Throwable) : ResumeState()
}