import AyCorners
import AySizes
import AySpacings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import fr.aylabs.ayphone.application.data.AyApp

@Composable
fun ApplicationLogo(
    app: AyApp,
    modifier: Modifier = Modifier,
    showTitle: Boolean = false,
    onClick: () -> Unit = {},
) {
    val cornerShape = RoundedCornerShape(AyCorners.l)
    val iconModifier = if (showTitle) {
        Modifier.size(AySizes.appIcon).clip(cornerShape)
    } else {
        Modifier.fillMaxWidth().aspectRatio(1f).clip(cornerShape)
    }
    val emojiStyle = if (showTitle) {
        MaterialTheme.typography.headlineSmall
    } else {
        MaterialTheme.typography.displaySmall
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() },
    ) {
        when {
            app.logo != null -> {
                Image(
                    painter = rememberVectorPainter(app.logo),
                    contentDescription = app.title,
                    modifier = iconModifier,
                )
            }

            app.iconEmoji != null -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = iconModifier.background(app.color),
                ) {
                    Text(
                        text = app.iconEmoji!!,
                        style = emojiStyle,
                    )
                }
            }

            else -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = iconModifier.background(app.color),
                ) {
                    Text(
                        text = app.title.firstOrNull()?.toString() ?: "?",
                        color = Color.White,
                        style = emojiStyle,
                    )
                }
            }
        }

        if (showTitle) {
            Spacer(Modifier.height(AySpacings.xs))
            Text(
                text = app.title,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
