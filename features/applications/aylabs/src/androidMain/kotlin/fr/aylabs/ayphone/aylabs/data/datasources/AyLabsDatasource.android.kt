package fr.aylabs.ayphone.aylabs.data.datasources

import fr.aylabs.ayphone.aylabs.AyLabsConfig

actual fun ayLabsStatsUrl(): String = AyLabsConfig.YOUTUBE_STATS_URL
actual fun proxyImageUrl(url: String?): String? = url
