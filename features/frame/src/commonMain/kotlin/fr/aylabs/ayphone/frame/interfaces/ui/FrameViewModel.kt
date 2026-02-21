package fr.aylabs.ayphone.frame.interfaces.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.ayshop.domain.InstallationRepository
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FrameViewModel(
    getResumeUseCase: GetResumeUseCase,
    val installationRepository: InstallationRepository
) : ViewModel() {
    private val mutableState = MutableStateFlow<FrameState>(FrameState.Initial)
    val state: StateFlow<FrameState> = mutableState

    init {
        viewModelScope.launch {
            runCatching {
                mutableState.value = FrameState.Loading
                getResumeUseCase()
            }.onSuccess { resume ->
                installationRepository.installedApps.collect { installedApps ->
                    mutableState.value = FrameState.Success(
                        name = resume.name,
                        role = resume.role,
                        installedApps = installedApps,
                    )
                }
            }.onFailure {
                mutableState.value = FrameState.Error(it)
            }
        }
    }
}