package fr.aylabs.ayphone.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AySpacings

@Composable

fun TextWidget(
    name: String,
    role: String,
    appTitleShown: Boolean = false
) {
    Surface(
        shape = RoundedCornerShape(AyCorners.l),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxSize()
            .padding(if (appTitleShown) AySpacings.s else 0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AySpacings.m, vertical = AySpacings.s),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            if (role.isNotBlank()) {
                Text(
                    text = role,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
