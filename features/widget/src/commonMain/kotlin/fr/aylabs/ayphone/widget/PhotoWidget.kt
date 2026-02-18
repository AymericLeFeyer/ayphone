package fr.aylabs.ayphone.widget

import AyCorners
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import ayphone.features.widget.generated.resources.Res
import ayphone.features.widget.generated.resources.me
import org.jetbrains.compose.resources.painterResource

class PhotoWidget(
    size: Int = 2,
) : Widget(size) {
    @Composable
    override fun Content() {
        Image(
            painter = painterResource(Res.drawable.me),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(AyCorners.l)),
            contentScale = ContentScale.Crop,
        )
    }
}
