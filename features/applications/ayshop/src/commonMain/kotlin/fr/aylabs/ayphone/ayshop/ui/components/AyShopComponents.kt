package fr.aylabs.ayphone.ayshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppIcon(
    emoji: String,
    color: Long,
    size: Dp = 56.dp,
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(size * 0.22f))
            .background(Color(color)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = emoji,
            style = when {
                size >= 72.dp -> MaterialTheme.typography.headlineMedium
                size >= 48.dp -> MaterialTheme.typography.titleLarge
                else -> MaterialTheme.typography.titleMedium
            },
        )
    }
}

@Composable
fun InstallButton(
    isInstalled: Boolean,
    isInstalling: Boolean,
    onInstall: () -> Unit,
    onUninstall: () -> Unit = {},
    onOpen: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    when {
        isInstalling -> {
            Button(
                onClick = {},
                enabled = false,
                modifier = modifier,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Spacer(Modifier.width(8.dp))
                Text("Installation…")
            }
        }

        isInstalled -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier,
            ) {
                Button(onClick = onOpen) { Text("Ouvrir") }
                OutlinedButton(onClick = onUninstall) { Text("Désinstaller") }
            }
        }

        else -> {
            Button(onClick = onInstall, modifier = modifier) { Text("Installer") }
        }
    }
}

@Composable
fun InstallButtonOnCard(
    isInstalled: Boolean,
    isInstalling: Boolean,
    onInstall: () -> Unit,
    onOpen: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    when {
        isInstalling -> {
            OutlinedButton(
                onClick = {},
                enabled = false,
                modifier = modifier,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(14.dp),
                    strokeWidth = 2.dp,
                    color = Color.White,
                )
                Spacer(Modifier.width(6.dp))
                Text("Installation…")
            }
        }

        isInstalled -> {
            OutlinedButton(
                onClick = onOpen,
                modifier = modifier,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
            ) {
                Text("Ouvrir")
            }
        }

        else -> {
            Button(
                onClick = onInstall,
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF7C3AED),
                ),
            ) {
                Text("Installer")
            }
        }
    }
}

@Composable
fun RatingRow(
    rating: Float,
    reviewCount: String,
    downloads: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "★ $rating",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFFFC107),
        )
        Text(
            text = "  ($reviewCount)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = "  •  $downloads",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
