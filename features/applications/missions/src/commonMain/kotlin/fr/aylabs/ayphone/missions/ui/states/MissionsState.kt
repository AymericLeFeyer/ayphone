package fr.aylabs.ayphone.missions.ui.states

import fr.aylabs.ayphone.resume.domain.models.Resume

sealed class MissionsState {
    data object Initial : MissionsState()
    data object Loading : MissionsState()
    data class Success(val data: Resume) : MissionsState()
    data class Error(val error: Throwable) : MissionsState()
}
