package fr.aylabs.ayphone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
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
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            val isWideScreen = maxWidth / maxHeight > 9f / 16f
            Box(
                modifier = if (isWideScreen) {
                    Modifier.fillMaxSize().aspectRatio(9 / 16f, matchHeightConstraintsFirst = true)
                } else {
                    Modifier.fillMaxSize()
                }
            ) {
                App(
                    onNavHostReady = { it.bindToBrowserNavigation() }
                )
            }
        }
    }
}