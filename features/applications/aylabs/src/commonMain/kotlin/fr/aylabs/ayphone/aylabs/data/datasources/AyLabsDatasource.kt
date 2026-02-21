package fr.aylabs.ayphone.aylabs.data.datasources

import fr.aylabs.ayphone.aylabs.AyLabsConfig
import fr.aylabs.ayphone.aylabs.data.dtos.AyLabsStatsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class AyLabsDatasource(private val client: HttpClient) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getStats(): AyLabsStatsDto? {
        return runCatching {
            val response = client.get(AyLabsConfig.YOUTUBE_STATS_URL)
            if (response.status == HttpStatusCode.OK) {
                json.decodeFromString(AyLabsStatsDto.serializer(), response.body<String>())
            } else null
        }.getOrNull()
    }
}
