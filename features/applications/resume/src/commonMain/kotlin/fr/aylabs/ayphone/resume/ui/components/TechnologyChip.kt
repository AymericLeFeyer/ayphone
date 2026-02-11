package fr.aylabs.ayphone.resume.ui.components

import AyColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TechnologyChip(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.labelSmall,
        color = AyColors.Primary,
        modifier = Modifier
            .background(
                AyColors.Primary.copy(alpha = 0.15f),
                RoundedCornerShape(4.dp),
            )
            .padding(horizontal = 6.dp, vertical = 2.dp),
    )
}
