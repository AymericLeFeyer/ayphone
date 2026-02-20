package fr.aylabs.ayphone.clients.ui.screens

import fr.aylabs.design_system.AyAppScaffold
import AySpacings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.system.ErrorWarningLine
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.clients.ui.states.ClientsState
import fr.aylabs.ayphone.clients.ui.viewmodels.ClientsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientsScreen(
    onBackClick: () -> Unit,
    onClientClick: (String) -> Unit,
    vm: ClientsViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by vm.state.collectAsStateWithLifecycle()

    AyAppScaffold(
        title = "Clients",
        containerColor = AyApp.CLIENTS.color,
        onBackClick = onBackClick,
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (val state = uiState) {
                is ClientsState.Initial,
                is ClientsState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ClientsState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            imageVector = Remix.System.ErrorWarningLine,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                        )
                        Text(
                            text = "Une erreur est survenue",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = AySpacings.s),
                        )
                        Button(
                            onClick = { coroutineScope.launch { vm.loadData() } },
                            modifier = Modifier.padding(top = AySpacings.l),
                        ) {
                            Text("Réessayer")
                        }
                    }
                }

                is ClientsState.Success -> {
                    ClientsReadyScreen(
                        resume = state.data,
                        onClientClick = onClientClick,
                    )
                }
            }
        }
    }
}
