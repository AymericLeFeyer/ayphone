package fr.aylabs.ayphone.stack.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.stack.ui.states.StackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StackViewModel(
    private val getResumeUseCase: GetResumeUseCase,
) : ViewModel() {

    private val mutableState = MutableStateFlow<StackState>(StackState.Initial)
    val state: StateFlow<StackState> = mutableState

    init {
        viewModelScope.launch { loadData() }
    }

    suspend fun loadData() {
        runCatching {
            mutableState.value = StackState.Loading
            getResumeUseCase()
        }.onSuccess {
            mutableState.value = StackState.Success(it)
        }.onFailure {
            mutableState.value = StackState.Error(it)
        }
    }
}
