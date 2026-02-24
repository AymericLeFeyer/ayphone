package fr.aylabs.ayphone.aylabs.data.datasources

import ayphone.features.applications.aylabs.generated.resources.Res
import fr.aylabs.ayphone.aylabs.data.dtos.AyLabsStatsDto
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

fun proxyImageUrl(url: String?): String? = url

class AyLabsDatasource {

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getStats(): AyLabsStatsDto? {
        return runCatching {
            val rawJson = Res.readBytes("files/youtube-stats.json").decodeToString()
            json.decodeFromString(AyLabsStatsDto.serializer(), rawJson)
        }.getOrNull()
    }
}
