package fr.aylabs.ayphone.clients.ui.states

import fr.aylabs.ayphone.resume.domain.models.Resume

sealed class ClientsState {
    data object Initial : ClientsState()
    data object Loading : ClientsState()
    data class Success(val data: Resume) : ClientsState()
    data class Error(val error: Throwable) : ClientsState()
}
