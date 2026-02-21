package fr.aylabs.ayphone.aylabs.domain.models

data class AyLabsVideo(
    val videoId: String,
    val title: String,
    val thumbnailUrl: String?,
    val publishedAt: String,
    val viewCount: Int?,
    val likeCount: Int?,
    val commentCount: Int?,
    val duration: String?,
)
