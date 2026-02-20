package fr.aylabs.ayphone.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ayphone.features.widget.generated.resources.Res
import ayphone.features.widget.generated.resources.me
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AySpacings
import org.jetbrains.compose.resources.painterResource

@Composable
fun PhotoWidget(
    appTitleShown: Boolean = false
) {
    Image(
        painter = painterResource(Res.drawable.me),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(if (appTitleShown) AySpacings.s else 0.dp)
            .clip(RoundedCornerShape(AyCorners.l)),
        contentScale = ContentScale.Crop,
    )
}
