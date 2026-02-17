package fr.aylabs.ayphone.about.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.about.ui.states.AboutState
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AboutViewModel(
    private val getResumeUseCase: GetResumeUseCase,
) : ViewModel() {

    private val mutableState = MutableStateFlow<AboutState>(AboutState.Initial)
    val state: StateFlow<AboutState> = mutableState

    init {
        viewModelScope.launch { loadData() }
    }

    suspend fun loadData() {
        runCatching {
            mutableState.value = AboutState.Loading
            getResumeUseCase()
        }.onSuccess {
            mutableState.value = AboutState.Success(it)
        }.onFailure {
            mutableState.value = AboutState.Error(it)
        }
    }
}
