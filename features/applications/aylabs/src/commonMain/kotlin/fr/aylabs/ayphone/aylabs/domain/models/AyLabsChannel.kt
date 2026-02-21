package fr.aylabs.ayphone.aylabs.domain.models

data class AyLabsChannel(
    val channelTitle: String,
    val channelDescription: String?,
    val avatarUrl: String?,
    val subscriberCount: Int,
    val viewCount: Int,
    val videoCount: Int,
    val customUrl: String?,
)
