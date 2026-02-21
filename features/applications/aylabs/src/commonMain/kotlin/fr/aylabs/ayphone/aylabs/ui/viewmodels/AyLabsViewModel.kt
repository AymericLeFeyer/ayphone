package fr.aylabs.ayphone.aylabs.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.aylabs.domain.usecases.GetAyLabsInfoUseCase
import fr.aylabs.ayphone.aylabs.ui.states.AyLabsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AyLabsViewModel(
    private val getAyLabsInfoUseCase: GetAyLabsInfoUseCase,
) : ViewModel() {

    private val mutableState = MutableStateFlow<AyLabsState>(AyLabsState.Initial)
    val state: StateFlow<AyLabsState> = mutableState

    init {
        viewModelScope.launch { loadData() }
    }

    suspend fun loadData() {
        runCatching {
            mutableState.value = AyLabsState.Loading
            getAyLabsInfoUseCase()
        }.onSuccess {
            mutableState.value = AyLabsState.Success(it)
        }.onFailure {
            mutableState.value = AyLabsState.Error(it)
        }
    }
}
