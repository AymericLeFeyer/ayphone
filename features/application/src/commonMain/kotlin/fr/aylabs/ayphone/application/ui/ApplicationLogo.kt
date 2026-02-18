import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.application.data.Application

@Composable
fun ApplicationLogo(
    app: Application,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { app.onClick() },
    ) {
        when {
            app.logo != null -> {
                Image(
                    painter = rememberVectorPainter(app.logo),
                    contentDescription = app.title,
                    modifier = Modifier.size(64.dp),
                )
            }

            app.iconEmoji != null -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(app.iconColor ?: 0xFF888888)),
                ) {
                    Text(
                        text = app.iconEmoji!!,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }

            else -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF888888)),
                ) {
                    Text(
                        text = app.title.firstOrNull()?.toString() ?: "?",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        }

        Spacer(Modifier.height(6.dp))

        Text(
            text = app.title,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
