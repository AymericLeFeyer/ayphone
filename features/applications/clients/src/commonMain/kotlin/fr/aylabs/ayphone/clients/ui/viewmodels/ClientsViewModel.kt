package fr.aylabs.ayphone.clients.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.clients.ui.states.ClientsState
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientsViewModel(
    private val getResumeUseCase: GetResumeUseCase,
) : ViewModel() {

    private val mutableState = MutableStateFlow<ClientsState>(ClientsState.Initial)
    val state: StateFlow<ClientsState> = mutableState

    init {
        viewModelScope.launch { loadData() }
    }

    suspend fun loadData() {
        runCatching {
            mutableState.value = ClientsState.Loading
            getResumeUseCase()
        }.onSuccess {
            mutableState.value = ClientsState.Success(it)
        }.onFailure {
            mutableState.value = ClientsState.Error(it)
        }
    }
}
