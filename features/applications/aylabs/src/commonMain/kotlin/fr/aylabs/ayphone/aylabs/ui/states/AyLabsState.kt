package fr.aylabs.ayphone.aylabs.ui.states

import fr.aylabs.ayphone.aylabs.domain.models.AyLabsInfo

sealed class AyLabsState {
    data object Initial : AyLabsState()
    data object Loading : AyLabsState()
    data class Success(val info: AyLabsInfo) : AyLabsState()
    data class Error(val error: Throwable) : AyLabsState()
}
