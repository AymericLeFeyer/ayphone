package fr.aylabs.ayphone.aylabs.domain.models

data class AyLabsInfo(
    val channel: AyLabsChannel? = null,
    val videos: List<AyLabsVideo> = emptyList(),
    val averageViews: Int? = null,
    val engagementRate: Double? = null,
)
