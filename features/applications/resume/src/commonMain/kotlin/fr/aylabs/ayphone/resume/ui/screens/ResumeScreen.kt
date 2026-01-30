package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.aylabs.ayphone.resume.ui.states.ResumeState
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResumeScreen(
    onBackClick: () -> Unit,
    vm: ResumeViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by vm.state.collectAsStateWithLifecycle()

    Column {
        Text("I'm the resume screen !")
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    vm.init()
                }
            }) {
                Text("Call API")
            }
            when (val state = uiState) {
                is ResumeState.Initial -> Text("Initial")
                is ResumeState.Loading -> Text("Loading...")
                is ResumeState.Success -> Text("Success: ${state.data.missions.size}")
                is ResumeState.Error -> Text("Error: ${state.error.message}")
            }
        }
        Button(onClick = onBackClick) {
            Text("Go back")
        }
    }
}