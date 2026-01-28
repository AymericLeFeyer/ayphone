package fr.aylabs.ayphone.application.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

abstract class Application(
    val title: String,
    val logo: ImageVector? = null,
) {
    abstract fun onClick(): Unit

    @Composable
    fun ApplicationLogo(
        modifier: Modifier = Modifier
    ) {
        if (logo != null) {
            Image(
                painter = rememberVectorPainter(logo),
                contentDescription = title,
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(96.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.LightGray)
                    .border(0.dp, Color.Transparent, MaterialTheme.shapes.medium)
            ) {
                Text(
                    text = title.firstOrNull()?.toString() ?: "?",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }

}