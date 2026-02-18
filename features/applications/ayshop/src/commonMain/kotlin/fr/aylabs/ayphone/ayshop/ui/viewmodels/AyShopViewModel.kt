package fr.aylabs.ayphone.ayshop.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.ayshop.domain.AVAILABLE_APPS
import fr.aylabs.ayphone.ayshop.domain.InstallationRepository
import fr.aylabs.ayphone.ayshop.ui.states.AyShopState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AyShopViewModel(
    private val installationRepository: InstallationRepository,
) : ViewModel() {

    private val _installingApps = MutableStateFlow<Set<String>>(emptySet())

    val state: StateFlow<AyShopState> = combine(
        installationRepository.installedApps,
        _installingApps,
    ) { installed, installing ->
        AyShopState.Success(
            apps = AVAILABLE_APPS,
            installedApps = installed,
            installingApps = installing,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AyShopState.Loading)

    fun install(appId: String) {
        viewModelScope.launch {
            _installingApps.update { it + appId }
            delay(1500)
            installationRepository.install(appId)
            _installingApps.update { it - appId }
        }
    }

    fun uninstall(appId: String) {
        viewModelScope.launch {
            installationRepository.uninstall(appId)
        }
    }
}
