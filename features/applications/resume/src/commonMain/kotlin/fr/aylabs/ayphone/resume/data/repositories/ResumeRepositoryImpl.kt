package fr.aylabs.ayphone.resume.data.repositories

import fr.aylabs.ayphone.resume.data.datasources.ResumeRemoteDatasource
import fr.aylabs.ayphone.resume.data.datasources.ResumeLocalDatasource
import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import fr.aylabs.ayphone.resume.data.mappers.toModel
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.repositories.ResumeRepository
import kotlin.time.Clock
import kotlinx.serialization.json.Json

class ResumeRepositoryImpl(
    private val remoteDatasource: ResumeRemoteDatasource,
    private val localDatasource: ResumeLocalDatasource,
) : ResumeRepository {

    private val jsonParser = Json { ignoreUnknownKeys = true }

    override suspend fun getResumeData(): Resume {
        val cachedJson = localDatasource.getCachedJson()
        val cachedTimestamp = localDatasource.getCachedTimestamp()

        if (cachedJson != null && cachedTimestamp != null) {
            val now = Clock.System.now()
            val age = now.toEpochMilliseconds() - cachedTimestamp
            if (age < CACHE_TTL_MS) {
                val dto = jsonParser.decodeFromString(ResumeDto.serializer(), cachedJson)
                return dto.toModel()
            }
        }

        val dto = remoteDatasource.getResumeData()
        val rawJson = jsonParser.encodeToString(ResumeDto.serializer(), dto)
        localDatasource.saveCache(rawJson, Clock.System.now().toEpochMilliseconds())
        return dto.toModel()
    }

    companion object {
        private const val CACHE_TTL_MS = 15 * 60 * 1000L // 15 minutes
    }
}
