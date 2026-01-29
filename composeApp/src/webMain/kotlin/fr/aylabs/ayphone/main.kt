package fr.aylabs.ayphone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToBrowserNavigation
import co.touchlab.kermit.Logger
import fr.aylabs.ayphone.di.initKoin

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    initKoin()
    Logger.setTag("AyPhone")

    ComposeViewport {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .aspectRatio(9 / 16f, true)
        ) {
            App(
                onNavHostReady = { it.bindToBrowserNavigation() }
            )
        }
    }
}