package fr.aylabs.ayphone.resume.ui.screens

import ResumeRemoteDatasource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import fr.aylabs.ayphone.resume.data.repositories.ResumeRepositoryImpl
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ResumeScreen(onBackClick: () -> Unit) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val datasource = ResumeRemoteDatasource()
    val repository = ResumeRepositoryImpl(datasource)
    val useCase = GetResumeUseCase(repository)
    val vm = ResumeViewModel(useCase)

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