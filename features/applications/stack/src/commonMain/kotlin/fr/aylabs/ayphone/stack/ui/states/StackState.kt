package fr.aylabs.ayphone.stack.ui.states

import fr.aylabs.ayphone.resume.domain.models.Resume

sealed class StackState {
    data object Initial : StackState()
    data object Loading : StackState()
    data class Success(val data: Resume) : StackState()
    data class Error(val error: Throwable) : StackState()
}
