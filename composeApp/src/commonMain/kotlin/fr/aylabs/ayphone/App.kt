package fr.aylabs.ayphone

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun App(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController: NavHostController = rememberNavController()

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    MaterialTheme {
        MainNavHost(
            navController = navController,
            onBackClick = navController::popBackStack
        )
    }
}