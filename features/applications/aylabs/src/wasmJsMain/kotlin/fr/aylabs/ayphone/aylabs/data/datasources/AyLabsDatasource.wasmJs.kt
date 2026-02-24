package fr.aylabs.ayphone.aylabs.data.datasources

import fr.aylabs.ayphone.aylabs.AyLabsConfig

actual fun ayLabsStatsUrl(): String = "/youtube-stats.json"
actual fun proxyImageUrl(url: String?): String? = url
