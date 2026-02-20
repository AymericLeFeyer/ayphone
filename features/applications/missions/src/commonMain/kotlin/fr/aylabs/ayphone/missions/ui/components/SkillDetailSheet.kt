package fr.aylabs.ayphone.missions.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.aylabs.ayphone.resume.domain.models.Skill
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillDetailSheet(
    skillName: String,
    description: String,
    frequency: Double = 0.0,
    onSeeSkillDetail: (() -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = AySpacings.l)
                .padding(top = AySpacings.xl, bottom = AySpacings.xl),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Skill.fromLabel(skillName)?.let { skill ->
                    SafeImage(
                        resourcePath = skill.iconPath,
                        contentDescription = null,
                        modifier = Modifier.size(AySizes.iconL),
                    )
                    Spacer(Modifier.width(AySpacings.s))
                }
                Text(
                    text = skillName,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                )
                if (frequency > 0.0) {
                    val percent = (frequency * 100).toInt()
                    Spacer(Modifier.width(AySpacings.s))
                    Text(
                        text = "${percent}% du temps",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            if (description.isNotBlank()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = AySpacings.xs),
                )
            }
            onSeeSkillDetail?.let { onClick ->
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = AySpacings.l),
                ) {
                    Text("Voir le détail")
                }
            }
        }
    }
}
