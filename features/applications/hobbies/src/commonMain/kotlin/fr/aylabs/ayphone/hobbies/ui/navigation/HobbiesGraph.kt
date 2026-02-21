package fr.aylabs.ayphone.hobbies.ui.navigation

import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesAction
import fr.aylabs.ayphone.hobbies.ui.screens.HobbiesScreen
import fr.aylabs.ayphone.hobbies.ui.viewmodels.HobbiesViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.hobbiesGraph(
    navController: NavController,
    onOpenAyLabs: () -> Unit,
) {
    navigation<HobbiesRoutes.Graph>(startDestination = HobbiesRoutes.Root) {
        composable<HobbiesRoutes.Root> {
            val vm: HobbiesViewModel = koinViewModel()
            val uriHandler = LocalUriHandler.current
            HobbiesScreen(
                onBackClick = navController::popBackStack,
                onAction = { action ->
                    when (action) {
                        is HobbiesAction.OpenUrl -> uriHandler.openUri(action.url)
                        HobbiesAction.OpenAyLabs -> onOpenAyLabs()
                    }
                },
                vm = vm,
            )
        }
    }
}
