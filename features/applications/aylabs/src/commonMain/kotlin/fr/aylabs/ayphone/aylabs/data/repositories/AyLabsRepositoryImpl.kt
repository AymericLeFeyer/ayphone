package fr.aylabs.ayphone.aylabs.data.repositories

import fr.aylabs.ayphone.aylabs.data.datasources.AyLabsDatasource
import fr.aylabs.ayphone.aylabs.data.datasources.AyLabsLocalDatasource
import fr.aylabs.ayphone.aylabs.data.dtos.AyLabsStatsDto
import fr.aylabs.ayphone.aylabs.data.mappers.mapToAyLabsInfo
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsInfo
import fr.aylabs.ayphone.aylabs.domain.repositories.AyLabsRepository
import kotlin.time.Clock
import kotlinx.serialization.json.Json

class AyLabsRepositoryImpl(
    private val datasource: AyLabsDatasource,
    private val localDatasource: AyLabsLocalDatasource,
) : AyLabsRepository {

    private val jsonParser = Json { ignoreUnknownKeys = true }

    override suspend fun getInfo(): AyLabsInfo {
        val cachedJson = localDatasource.getCachedJson()
        val cachedTimestamp = localDatasource.getCachedTimestamp()

        if (cachedJson != null && cachedTimestamp != null) {
            val age = Clock.System.now().toEpochMilliseconds() - cachedTimestamp
            if (age < CACHE_TTL_MS) {
                val dto = jsonParser.decodeFromString(AyLabsStatsDto.serializer(), cachedJson)
                return mapToAyLabsInfo(dto)
            }
        }

        val dto = datasource.getStats()
        if (dto != null) {
            val rawJson = jsonParser.encodeToString(AyLabsStatsDto.serializer(), dto)
            localDatasource.saveCache(rawJson, Clock.System.now().toEpochMilliseconds())
        }
        return mapToAyLabsInfo(dto)
    }

    companion object {
        private const val CACHE_TTL_MS = 15 * 60 * 1000L // 15 minutes
    }
}
