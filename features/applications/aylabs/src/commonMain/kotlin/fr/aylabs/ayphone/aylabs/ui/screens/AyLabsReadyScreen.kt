package fr.aylabs.ayphone.aylabs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Buildings
import com.woowla.compose.icon.collections.remix.remix.Communication
import com.woowla.compose.icon.collections.remix.remix.Device
import com.woowla.compose.icon.collections.remix.remix.Document
import com.woowla.compose.icon.collections.remix.remix.Logos
import com.woowla.compose.icon.collections.remix.remix.Map
import com.woowla.compose.icon.collections.remix.remix.Media
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.UserAndFaces
import com.woowla.compose.icon.collections.remix.remix.buildings.HomeLine
import com.woowla.compose.icon.collections.remix.remix.communication.MessageLine
import com.woowla.compose.icon.collections.remix.remix.device.CpuLine
import com.woowla.compose.icon.collections.remix.remix.device.ServerLine
import com.woowla.compose.icon.collections.remix.remix.document.BookLine
import com.woowla.compose.icon.collections.remix.remix.logos.DiscordLine
import com.woowla.compose.icon.collections.remix.remix.logos.GithubLine
import com.woowla.compose.icon.collections.remix.remix.logos.InstagramLine
import com.woowla.compose.icon.collections.remix.remix.logos.YoutubeLine
import com.woowla.compose.icon.collections.remix.remix.map.GlobeLine
import com.woowla.compose.icon.collections.remix.remix.media.FilmLine
import com.woowla.compose.icon.collections.remix.remix.media.VideoLine
import com.woowla.compose.icon.collections.remix.remix.system.EyeLine
import com.woowla.compose.icon.collections.remix.remix.system.ExternalLinkLine
import com.woowla.compose.icon.collections.remix.remix.system.ThumbUpLine
import com.woowla.compose.icon.collections.remix.remix.system.TimerLine
import com.woowla.compose.icon.collections.remix.remix.userandfaces.GroupLine
import fr.aylabs.ayphone.aylabs.AyLabsConfig
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsChannel
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsInfo
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsVideo
import fr.aylabs.design_system.AyCorners
import fr.aylabs.design_system.AySizes
import fr.aylabs.design_system.AySpacings

@Composable
fun AyLabsReadyScreen(info: AyLabsInfo) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(AySpacings.l),
        verticalArrangement = Arrangement.spacedBy(AySpacings.l),
    ) {
        HeaderCard(info.channel, uriHandler)

        info.channel?.let { channel ->
            StatsRow(channel)
        }

        val latestVideo = info.videos.firstOrNull()
        val moreVideos = info.videos.drop(1).take(4)

        latestVideo?.let { video ->
            Column(verticalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                Text(
                    text = "Dernière vidéo",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                LatestVideoCard(video, uriHandler)
            }
        }

        if (moreVideos.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                Text(
                    text = "Vidéos récentes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                moreVideos.forEach { video ->
                    CompactVideoRow(video, uriHandler)
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(AySpacings.s)) {
            Text(
                text = "Thèmes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
            ) {
                TopicChip(Modifier.weight(1f), Remix.Buildings.HomeLine, "Domotique")
                TopicChip(Modifier.weight(1f), Remix.Device.ServerLine, "Homelab")
                TopicChip(Modifier.weight(1f), Remix.Device.CpuLine, "3D Print")
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(AySpacings.s)) {
            Text(
                text = "Liens",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            LinkItem(Remix.Logos.YoutubeLine, "Chaîne YouTube", AyLabsConfig.YOUTUBE_CHANNEL_URL, uriHandler)
            LinkItem(Remix.Map.GlobeLine, "Site web", AyLabsConfig.WEBSITE_URL, uriHandler)
            LinkItem(Remix.Document.BookLine, "Documentation", AyLabsConfig.DOCS_URL, uriHandler)
            LinkItem(Remix.Logos.GithubLine, "GitHub", AyLabsConfig.GITHUB_URL, uriHandler)
            if (AyLabsConfig.DISCORD_URL.isNotEmpty()) {
                LinkItem(Remix.Logos.DiscordLine, "Discord", AyLabsConfig.DISCORD_URL, uriHandler)
            }
            if (AyLabsConfig.INSTAGRAM_URL.isNotEmpty()) {
                LinkItem(Remix.Logos.InstagramLine, "Instagram", AyLabsConfig.INSTAGRAM_URL, uriHandler)
            }
        }

        Spacer(Modifier.height(AySpacings.l))
    }
}

@Composable
private fun HeaderCard(channel: AyLabsChannel?, uriHandler: UriHandler) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(AyCorners.m),
            )
            .padding(AySpacings.l),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AySpacings.s),
    ) {
        if (channel?.avatarUrl != null) {
            AsyncImage(
                model = channel.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        } else {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Remix.Media.FilmLine,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(AySizes.iconXxl),
                )
            }
        }

        Text(
            text = channel?.channelTitle ?: "AyLabs",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        channel?.customUrl?.let { url ->
            Text(
                text = url,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        channel?.channelDescription?.trim()?.let { description ->
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = AySpacings.s),
                )
            }
        } ?: Text(
            text = "Domotique • Homelab • Impression 3D • Tech",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AySpacings.s),
            modifier = Modifier.padding(top = AySpacings.xs),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                Button(onClick = { uriHandler.openUri(AyLabsConfig.YOUTUBE_CHANNEL_URL) }) {
                    Text("YouTube")
                }
                OutlinedButton(onClick = { uriHandler.openUri(AyLabsConfig.WEBSITE_URL) }) {
                    Text("Site web")
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                OutlinedButton(onClick = { uriHandler.openUri(AyLabsConfig.GITHUB_URL) }) {
                    Text("GitHub")
                }
                if (AyLabsConfig.DISCORD_URL.isNotEmpty()) {
                    OutlinedButton(onClick = { uriHandler.openUri(AyLabsConfig.DISCORD_URL) }) {
                        Text("Discord")
                    }
                }
            }
        }
    }
}

@Composable
private fun StatsRow(channel: AyLabsChannel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
    ) {
        StatCard(Modifier.weight(1f), Remix.UserAndFaces.GroupLine, "Abonnés", formatCount(channel.subscriberCount))
        StatCard(Modifier.weight(1f), Remix.Media.VideoLine, "Vidéos", formatCount(channel.videoCount))
        StatCard(Modifier.weight(1f), Remix.System.EyeLine, "Vues", formatCount(channel.viewCount))
    }
}

@Composable
private fun StatCard(modifier: Modifier, icon: ImageVector, label: String, value: String) {
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(AyCorners.s),
            )
            .padding(AySpacings.m),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AySpacings.xxs),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(AySizes.iconL),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun LatestVideoCard(video: AyLabsVideo, uriHandler: UriHandler) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AyCorners.m))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { uriHandler.openUri("https://www.youtube.com/watch?v=${video.videoId}") },
    ) {
        video.thumbnailUrl?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier.padding(AySpacings.m),
            verticalArrangement = Arrangement.spacedBy(AySpacings.xs),
        ) {
            Text(
                text = video.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(AySpacings.m),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = video.publishedAt.take(10),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                formatDuration(video.duration)?.let { duration ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AySpacings.xxs),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Remix.System.TimerLine,
                            contentDescription = null,
                            modifier = Modifier.size(AySizes.iconXs),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Text(
                            text = duration,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(AySpacings.m),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                video.viewCount?.let {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AySpacings.xxs),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(imageVector = Remix.System.EyeLine, contentDescription = null, modifier = Modifier.size(AySizes.iconXs))
                        Text(formatCount(it), style = MaterialTheme.typography.labelSmall)
                    }
                }
                video.likeCount?.let {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AySpacings.xxs),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(imageVector = Remix.System.ThumbUpLine, contentDescription = null, modifier = Modifier.size(AySizes.iconXs))
                        Text(formatCount(it), style = MaterialTheme.typography.labelSmall)
                    }
                }
                video.commentCount?.let {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AySpacings.xxs),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(imageVector = Remix.Communication.MessageLine, contentDescription = null, modifier = Modifier.size(AySizes.iconXs))
                        Text(formatCount(it), style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

@Composable
private fun CompactVideoRow(video: AyLabsVideo, uriHandler: UriHandler) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AyCorners.s))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { uriHandler.openUri("https://www.youtube.com/watch?v=${video.videoId}") }
            .padding(AySpacings.s),
        horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        video.thumbnailUrl?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .size(width = 100.dp, height = 56.dp)
                    .clip(RoundedCornerShape(AyCorners.xs)),
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AySpacings.xxs),
        ) {
            Text(
                text = video.title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(AySpacings.s)) {
                Text(
                    text = video.publishedAt.take(10),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                formatDuration(video.duration)?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
        video.viewCount?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AySpacings.xxs),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Remix.System.EyeLine,
                    contentDescription = null,
                    modifier = Modifier.size(AySizes.iconXs),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = formatCount(it),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun TopicChip(modifier: Modifier, icon: ImageVector, label: String) {
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(AyCorners.s),
            )
            .padding(vertical = AySpacings.m, horizontal = AySpacings.s),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AySpacings.xs),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(AySizes.iconXl),
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun LinkItem(icon: ImageVector, label: String, url: String, uriHandler: UriHandler) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AyCorners.s))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { uriHandler.openUri(url) }
            .padding(horizontal = AySpacings.l, vertical = AySpacings.m),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(AySpacings.s),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(AySizes.iconM),
            )
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
        }
        Icon(
            imageVector = Remix.System.ExternalLinkLine,
            contentDescription = null,
            modifier = Modifier.size(AySizes.iconM),
        )
    }
}

private fun formatCount(count: Int): String {
    return when {
        count >= 1_000_000 -> "${count / 1_000_000}M"
        count >= 1_000 -> "${count / 1_000}k"
        else -> count.toString()
    }
}

private fun formatDuration(duration: String?): String? {
    if (duration == null) return null
    val regex = Regex("""PT(?:(\d+)H)?(?:(\d+)M)?(?:(\d+)S)?""")
    val match = regex.find(duration) ?: return null
    val hours = match.groupValues[1].toIntOrNull() ?: 0
    val minutes = match.groupValues[2].toIntOrNull() ?: 0
    val seconds = match.groupValues[3].toIntOrNull() ?: 0
    return if (hours > 0) {
        "$hours:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    } else {
        "$minutes:${seconds.toString().padStart(2, '0')}"
    }
}
