package fr.aylabs.ayphone.stack.ui.components

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
import fr.aylabs.ayphone.resume.domain.models.ResumeSkill
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillDetailSheet(
    resumeSkill: ResumeSkill,
    onSeeRelatedMissions: (() -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    val skill = resumeSkill.skill

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = AySpacings.l)
                .padding(bottom = AySpacings.xl),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SafeImage(
                    url = skill.iconUrl,
                    contentDescription = null,
                    modifier = Modifier.size(AySizes.iconL),
                )
                Spacer(Modifier.width(AySpacings.s))
                Text(
                    text = skill.label,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                )
                val years = resumeSkill.totalMonths / 12
                val months = resumeSkill.totalMonths % 12
                val durationText = buildString {
                    if (years > 0) append("${years} an${if (years > 1) "s" else ""}")
                    if (years > 0 && months > 0) append(" et ")
                    if (months > 0) append("$months mois")
                    append(" d'utilisation")
                }
                if (durationText.isNotBlank()) {
                    Spacer(Modifier.width(AySpacings.s))
                    Text(
                        text = durationText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            if (skill.description.isNotBlank()) {
                Text(
                    text = skill.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = AySpacings.s),
                )
            }
            onSeeRelatedMissions?.let { onClick ->
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = AySpacings.l),
                ) {
                    Text("Voir les missions associées")
                }
            }
        }
    }
}
