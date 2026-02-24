package fr.aylabs.ayphone.aylabs.data.datasources

import ayphone.features.applications.aylabs.generated.resources.Res
import fr.aylabs.ayphone.aylabs.data.dtos.AyLabsStatsDto
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.readBytes

fun proxyImageUrl(url: String?): String? = url

class AyLabsDatasource {

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getStats(): AyLabsStatsDto? {
        return runCatching {
            val rawJson = readBytes(Res.files.youtube_stats_json).decodeToString()
            json.decodeFromString(AyLabsStatsDto.serializer(), rawJson)
        }.getOrNull()
    }
}
