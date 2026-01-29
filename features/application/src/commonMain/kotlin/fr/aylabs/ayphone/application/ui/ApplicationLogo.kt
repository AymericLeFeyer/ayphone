import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.application.data.Application

@Composable
fun ApplicationLogo(
    app: Application,
    modifier: Modifier = Modifier
) {
    Box(modifier.clickable { app.onClick() }) {
        if (app.logo != null) {
            Image(
                painter = rememberVectorPainter(app.logo),
                contentDescription = app.title,
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(96.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.LightGray)
                    .border(0.dp, Color.Transparent, MaterialTheme.shapes.medium)
            ) {
                Text(
                    text = app.title.firstOrNull()?.toString() ?: "?",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}