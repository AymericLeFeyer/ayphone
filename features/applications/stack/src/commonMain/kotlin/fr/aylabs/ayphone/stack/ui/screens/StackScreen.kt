package fr.aylabs.ayphone.stack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Design
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.design.LayoutLine
import com.woowla.compose.icon.collections.remix.remix.system.ErrorWarningLine
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.stack.ui.states.StackState
import fr.aylabs.ayphone.stack.ui.viewmodels.StackViewModel
import fr.aylabs.design_system.AyAppScaffold
import fr.aylabs.design_system.AySpacings
import kotlinx.coroutines.launch

enum class StackGrouping { CATEGORY, DURATION }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StackScreen(
    onBackClick: () -> Unit,
    onSkillClick: (String) -> Unit,
    vm: StackViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by vm.state.collectAsStateWithLifecycle()
    var grouping by remember { mutableStateOf(StackGrouping.CATEGORY) }

    AyAppScaffold(
        title = "Stack technique",
        containerColor = AyApp.STACK.color,
        onBackClick = onBackClick,
        actions = {
            IconButton(onClick = {
                grouping = when (grouping) {
                    StackGrouping.CATEGORY -> StackGrouping.DURATION
                    StackGrouping.DURATION -> StackGrouping.CATEGORY
                }
            }) {
                Icon(
                    imageVector = Remix.Design.LayoutLine,
                    contentDescription = "Changer le groupement",
                )
            }
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (val state = uiState) {
                is StackState.Initial,
                is StackState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is StackState.Error -> {
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

                is StackState.Success -> {
                    StackReadyScreen(
                        resume = state.data,
                        grouping = grouping,
                        onSkillClick = onSkillClick,
                    )
                }
            }
        }
    }
}
