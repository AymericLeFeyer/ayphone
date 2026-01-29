package fr.aylabs.ayphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import fr.aylabs.ayphone.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dependency injection
        initKoin {
            androidContext(this@MainActivity)
            androidLogger()
        }

        // Logger
        Logger.setTag("AyPhone")

        // App
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
            )
            {
                App()
            }
        }
    }
}
