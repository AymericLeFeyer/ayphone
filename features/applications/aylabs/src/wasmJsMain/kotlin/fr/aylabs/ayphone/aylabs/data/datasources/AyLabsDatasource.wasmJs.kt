package fr.aylabs.ayphone.aylabs.data.datasources

import fr.aylabs.ayphone.aylabs.AyLabsConfig

actual fun ayLabsStatsUrl(): String = "https://corsproxy.io/?${AyLabsConfig.YOUTUBE_STATS_URL}"
actual fun proxyImageUrl(url: String?): String? = url?.let { "https://corsproxy.io/?$it" }
