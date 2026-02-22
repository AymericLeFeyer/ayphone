package fr.aylabs.ayphone.application.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import fr.aylabs.ayphone.application.data.AyApp
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings
import kotlinx.coroutines.delay

@Composable
fun ApplicationLogo(
    app: AyApp,
    modifier: Modifier = Modifier,
    showTitle: Boolean = false,
    animationDelayMs: Int = 0,
    onClick: () -> Unit = {},
) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(animationDelayMs.toLong())
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium,
            )
        )
    }

    val cornerShape = RoundedCornerShape(AyCorners.l)
    val iconModifier = if (showTitle) {
        Modifier.size(AySizes.appIcon)
    } else {
        Modifier.fillMaxWidth().aspectRatio(1f)
    }.clip(cornerShape)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
        }
    ) {
        Surface(
            modifier = Modifier
                .clickable { onClick() }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = iconModifier.background(app.color),
            ) {
                Icon(
                    imageVector = app.logo,
                    contentDescription = app.title,
                    tint = Color.White,
                    modifier = Modifier.fillMaxWidth(0.55f).aspectRatio(1f),
                )
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
