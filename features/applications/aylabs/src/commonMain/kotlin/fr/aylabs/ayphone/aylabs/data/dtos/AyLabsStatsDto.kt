package fr.aylabs.ayphone.aylabs.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AyLabsStatsDto(
    @SerialName("stats") val stats: AyLabsChannelStatsDto,
    @SerialName("videos") val videos: List<AyLabsVideoWrapperDto> = emptyList(),
    @SerialName("averageViews") val averageViews: Int? = null,
    @SerialName("engagementRate") val engagementRate: Double? = null,
    @SerialName("recentVideosCount") val recentVideosCount: Int? = null,
)

@Serializable
data class AyLabsChannelStatsDto(
    @SerialName("subscriberCount") val subscriberCount: Int,
    @SerialName("viewCount") val viewCount: Int,
    @SerialName("videoCount") val videoCount: Int,
    @SerialName("channelTitle") val channelTitle: String,
    @SerialName("channelDescription") val channelDescription: String? = null,
    @SerialName("thumbnails") val thumbnails: AyLabsThumbnailsDto? = null,
    @SerialName("customUrl") val customUrl: String? = null,
    @SerialName("publishedAt") val publishedAt: String? = null,
)

@Serializable
data class AyLabsVideoWrapperDto(
    @SerialName("json") val json: AyLabsVideoDto,
)

@Serializable
data class AyLabsVideoDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("viewCount") val viewCount: Int? = null,
    @SerialName("likeCount") val likeCount: Int? = null,
    @SerialName("commentCount") val commentCount: Int? = null,
    @SerialName("duration") val duration: String? = null,
    @SerialName("thumbnails") val thumbnails: AyLabsThumbnailsDto? = null,
)

@Serializable
data class AyLabsThumbnailsDto(
    @SerialName("default") val default: AyLabsThumbnailDto? = null,
    @SerialName("medium") val medium: AyLabsThumbnailDto? = null,
    @SerialName("high") val high: AyLabsThumbnailDto? = null,
    @SerialName("standard") val standard: AyLabsThumbnailDto? = null,
    @SerialName("maxres") val maxres: AyLabsThumbnailDto? = null,
)

@Serializable
data class AyLabsThumbnailDto(
    @SerialName("url") val url: String,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
)
