package fr.aylabs.ayphone.aylabs.data.mappers

import fr.aylabs.ayphone.aylabs.data.dtos.AyLabsStatsDto
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsChannel
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsInfo
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsVideo

fun mapToAyLabsInfo(dto: AyLabsStatsDto?): AyLabsInfo {
    if (dto == null) return AyLabsInfo()

    val channel = AyLabsChannel(
        channelTitle = dto.stats.channelTitle,
        channelDescription = dto.stats.channelDescription,
        avatarUrl = dto.stats.thumbnails?.medium?.url ?: dto.stats.thumbnails?.default?.url,
        subscriberCount = dto.stats.subscriberCount,
        viewCount = dto.stats.viewCount,
        videoCount = dto.stats.videoCount,
        customUrl = dto.stats.customUrl,
    )

    val videos = dto.videos.map { wrapper ->
        AyLabsVideo(
            videoId = wrapper.json.id,
            title = wrapper.json.title,
            thumbnailUrl = wrapper.json.thumbnails?.maxres?.url
                ?: wrapper.json.thumbnails?.standard?.url
                ?: wrapper.json.thumbnails?.high?.url
                ?: wrapper.json.thumbnails?.medium?.url,
            publishedAt = wrapper.json.publishedAt,
            viewCount = wrapper.json.viewCount,
            likeCount = wrapper.json.likeCount,
            commentCount = wrapper.json.commentCount,
            duration = wrapper.json.duration,
        )
    }

    return AyLabsInfo(
        channel = channel,
        videos = videos,
        averageViews = dto.averageViews,
        engagementRate = dto.engagementRate,
    )
}
