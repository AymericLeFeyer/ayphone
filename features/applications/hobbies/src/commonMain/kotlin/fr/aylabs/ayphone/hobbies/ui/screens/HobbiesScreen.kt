package fr.aylabs.ayphone.hobbies.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesAction
import fr.aylabs.ayphone.hobbies.ui.viewmodels.HobbiesViewModel
import fr.aylabs.design_system.AyAppScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbiesScreen(
    onBackClick: () -> Unit,
    onAction: (HobbiesAction) -> Unit,
    vm: HobbiesViewModel,
) {
    AyAppScaffold(
        title = "Loisirs",
        containerColor = AyApp.HOBBIES.color,
        onBackClick = onBackClick,
    ) { padding ->
        HobbiesReadyScreen(
            sections = vm.sections,
            onAction = onAction,
            modifier = Modifier.padding(padding),
        )
    }
}
