package fr.aylabs.ayphone

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.aylabs.ayphone.settings.AppPreferences
import fr.aylabs.ayphone.settings.AppTheme
import org.koin.compose.koinInject

@Composable
fun App(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController: NavHostController = rememberNavController()
    val appPreferences: AppPreferences = koinInject()
    val theme by appPreferences.theme.collectAsStateWithLifecycle()

    val darkTheme = when (theme) {
        AppTheme.SYSTEM -> isSystemInDarkTheme()
        AppTheme.LIGHT -> false
        AppTheme.DARK -> true
    }

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    MaterialTheme(
        colorScheme = (if (darkTheme) darkColorScheme() else lightColorScheme()).copy(
            primary = AyColors.Primary
        )
    ) {
        MainNavHost(
            navController = navController,
            onBackClick = navController::popBackStack
        )
    }
}
