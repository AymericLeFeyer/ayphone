package fr.aylabs.ayphone.resume.ui

import ResumeDatasourceImpl
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ResumeScreen(onBackClick: () -> Unit) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val datasource = ResumeDatasourceImpl()

    Column {

        Text("I'm the resume screen !")
        Button(onClick = {
            coroutineScope.launch {
                val result = datasource.getResumeData()
                println("API Result: $result")
            }
        }) {
            Text("Call API")
        }
        Button(onClick = onBackClick) {
            Text("Go back")
        }
    }
}