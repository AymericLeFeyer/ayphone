package fr.aylabs.ayphone.missions.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import ayphone.features.applications.resume.generated.resources.Res
import coil3.compose.AsyncImage

@Composable
fun SafeImage(
    resourcePath: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        model = Res.getUri(resourcePath),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
    )
}
