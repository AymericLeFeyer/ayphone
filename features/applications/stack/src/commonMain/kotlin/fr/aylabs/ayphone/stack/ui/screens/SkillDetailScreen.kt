package fr.aylabs.ayphone.stack.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.aylabs.ayphone.resume.domain.models.ResumeSkill
import fr.aylabs.ayphone.stack.ui.components.SafeImage
import fr.aylabs.design_system.AyDetailScaffold
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillDetailScreen(
    resumeSkill: ResumeSkill,
    onBackClick: () -> Unit,
    onSeeRelatedMissions: () -> Unit,
) {
    val skill = resumeSkill.skill

    AyDetailScaffold(
        title = skill.label,
        onBackClick = onBackClick,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(AySpacings.l),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SafeImage(
                    resourcePath = skill.iconPath,
                    contentDescription = null,
                    modifier = Modifier.size(AySizes.iconXxxl),
                )
                Spacer(Modifier.width(AySpacings.m))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = skill.label,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Text(
                        text = skill.category.label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            val years = resumeSkill.totalMonths / 12
            val months = resumeSkill.totalMonths % 12
            val durationText = buildString {
                if (years > 0) append("${years} an${if (years > 1) "s" else ""}")
                if (years > 0 && months > 0) append(" et ")
                if (months > 0) append("$months mois")
                append(" d'utilisation")
            }
            if (durationText.isNotBlank()) {
                Text(
                    text = durationText,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = AySpacings.l),
                )
            }

            if (skill.description.isNotBlank()) {
                Text(
                    text = skill.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = AySpacings.m),
                )
            }

            Button(
                onClick = onSeeRelatedMissions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AySpacings.xl),
            ) {
                Text("Voir les missions associées")
            }
        }
    }
}
