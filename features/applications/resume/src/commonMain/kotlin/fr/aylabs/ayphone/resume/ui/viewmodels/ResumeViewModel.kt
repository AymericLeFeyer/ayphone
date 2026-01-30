package fr.aylabs.ayphone.resume.ui.viewmodels

import androidx.lifecycle.ViewModel
import fr.aylabs.ayphone.resume.domain.usecases.GetDarkModeUseCase
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.resume.domain.usecases.SetDarkModeUseCase
import fr.aylabs.ayphone.resume.ui.states.ResumeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResumeViewModel(
    private val getResumeUseCase: GetResumeUseCase,
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase
) : ViewModel() {

    private val mutableState = MutableStateFlow<ResumeState>(ResumeState.Initial)
    val state: StateFlow<ResumeState> = mutableState

    private val mutableDarkModeState = MutableStateFlow<Boolean>(getDarkModeUseCase())
    val darkModeState: StateFlow<Boolean> = mutableDarkModeState

    suspend fun init() {
        runCatching {
            mutableState.value = ResumeState.Loading
            getResumeUseCase()
        }.onSuccess {
            mutableState.value = ResumeState.Success(it)
        }.onFailure {
            mutableState.value = ResumeState.Error(it)
        }
    }

    fun toggleDarkMode() {
        mutableDarkModeState.value.let {
            setDarkModeUseCase(!it)
            mutableDarkModeState.value = !it
        }
    }
}