package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun ResumeScreen(
    onBackClick: () -> Unit,
    vm: ResumeViewModel = koinInject()
) {
    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("I'm the resume screen !")
        Button(onClick = {
            coroutineScope.launch {
                vm.init()
            }
        }) {
            Text("Call API")
        }
        Button(onClick = onBackClick) {
            Text("Go back")
        }
    }
}