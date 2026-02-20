package fr.aylabs.ayphone.frame.interfaces.ui

sealed class FrameState {
    data object Initial : FrameState()
    data object Loading : FrameState()
    data class Success(
        val name: String,
        val role: String,
        val installedApps: Set<String>
    ) :
        FrameState()

    data class Error(val error: Throwable) : FrameState()
}