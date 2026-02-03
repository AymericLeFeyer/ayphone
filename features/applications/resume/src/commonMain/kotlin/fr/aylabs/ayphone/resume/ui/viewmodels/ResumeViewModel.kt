package fr.aylabs.ayphone.resume.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.resume.ui.states.ResumeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResumeViewModel(
    private val getResumeUseCase: GetResumeUseCase,
) : ViewModel() {

    private val mutableState = MutableStateFlow<ResumeState>(ResumeState.Initial)
    val state: StateFlow<ResumeState> = mutableState

    init {
        viewModelScope.launch { loadData() }
    }

    suspend fun loadData() {
        runCatching {
            mutableState.value = ResumeState.Loading
            getResumeUseCase()
        }.onSuccess {
            mutableState.value = ResumeState.Success(it)
        }.onFailure {
            mutableState.value = ResumeState.Error(it)
        }
    }

}