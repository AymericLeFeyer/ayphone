package fr.aylabs.ayphone.resume.ui.components

import AyColors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.ResumeMissionTechnology

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnologyDetailSheet(
    technology: ResumeMissionTechnology,
    onSeeRelatedMissions: () -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
        ) {
            Text(
                text = technology.name,
                style = MaterialTheme.typography.titleLarge,
            )
            if (technology.comments.isNotBlank()) {
                Text(
                    text = technology.comments,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            Button(
                onClick = onSeeRelatedMissions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text("Voir les missions associées")
            }
        }
    }
}
