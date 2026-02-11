package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Arrows
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.arrows.ArrowLeftSLine
import com.woowla.compose.icon.collections.remix.remix.system.LoopLeftFill
import fr.aylabs.ayphone.resume.ui.states.ResumeState
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeScreen(
    onBackClick: () -> Unit,
    vm: ResumeViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by vm.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Surface {
                TopAppBar(
                    title = {
                        Text(
                            text = "Expériences",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Remix.Arrows.ArrowLeftSLine,
                                contentDescription = "Back",
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { coroutineScope.launch { vm.loadData() } }) {
                            Icon(
                                imageVector = Remix.System.LoopLeftFill,
                                contentDescription = null,
                            )
                        }
                    },
                )
            }
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (val state = uiState) {
                is ResumeState.Initial,
                is ResumeState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ResumeState.Error -> {
                    Text("An error occurred: ${state.error}")

                }

                is ResumeState.Success -> {
                    ResumeScreenReady(
                        resume = state.data
                    )
                }
            }
        }
    }
}