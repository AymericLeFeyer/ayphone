package fr.aylabs.ayphone.resume.ui.components

import AyColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.Technology

@Composable
fun TechnologyChip(name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                AyColors.Primary.copy(alpha = 0.15f),
                RoundedCornerShape(4.dp),
            )
            .padding(horizontal = 6.dp, vertical = 2.dp),
    ) {
        Technology.fromLabel(name)?.let { tech ->
            SafeImage(
                resource = tech.icon,
                contentDescription = null,
                modifier = Modifier.size(14.dp).padding(end = 4.dp),
            )
        }
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            color = AyColors.Primary,
        )
    }
}
