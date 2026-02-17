package fr.aylabs.ayphone.about.ui.states

import fr.aylabs.ayphone.resume.domain.models.Resume

sealed class AboutState {
    data object Initial : AboutState()
    data object Loading : AboutState()
    data class Success(val data: Resume) : AboutState()
    data class Error(val error: Throwable) : AboutState()
}
