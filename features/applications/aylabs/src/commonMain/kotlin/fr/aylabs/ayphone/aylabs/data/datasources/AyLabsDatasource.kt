package fr.aylabs.ayphone.aylabs.data.datasources

import fr.aylabs.ayphone.aylabs.AyLabsConfig
import fr.aylabs.ayphone.aylabs.data.dtos.AyLabsStatsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

fun proxyImageUrl(url: String?): String? = url

class AyLabsDatasource(private val client: HttpClient) {

    suspend fun getStats(): AyLabsStatsDto? {
        return runCatching {
            client.get(AyLabsConfig.YOUTUBE_STATS_URL).body<AyLabsStatsDto>()
        }.getOrNull()
    }
}
