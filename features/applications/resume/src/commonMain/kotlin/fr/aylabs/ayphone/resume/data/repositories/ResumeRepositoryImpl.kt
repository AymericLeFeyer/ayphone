package fr.aylabs.ayphone.resume.data.repositories

import fr.aylabs.ayphone.resume.data.datasources.ResumeRemoteDatasource
import fr.aylabs.ayphone.resume.data.datasources.ResumeLocalDatasource
import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import fr.aylabs.ayphone.resume.data.mappers.toModel
import fr.aylabs.ayphone.resume.domain.models.Company
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.models.Skill
import fr.aylabs.ayphone.resume.domain.repositories.ResumeRepository
import kotlin.time.Clock
import kotlinx.serialization.json.Json

class ResumeRepositoryImpl(
    private val remoteDatasource: ResumeRemoteDatasource,
    private val localDatasource: ResumeLocalDatasource,
) : ResumeRepository {

    private val jsonParser = Json { ignoreUnknownKeys = true }
    private var registriesPopulated = false

    override suspend fun getResumeData(): Resume {
        val cachedJson = localDatasource.getCachedJson()
        val cachedTimestamp = localDatasource.getCachedTimestamp()

        if (cachedJson != null && cachedTimestamp != null) {
            val now = Clock.System.now()
            val age = now.toEpochMilliseconds() - cachedTimestamp
            if (age < CACHE_TTL_MS) {
                if (!registriesPopulated) populateRegistries()
                val dto = jsonParser.decodeFromString(ResumeDto.serializer(), cachedJson)
                return dto.toModel()
            }
        }

        populateRegistries()
        val dto = remoteDatasource.getResumeData()
        val rawJson = jsonParser.encodeToString(ResumeDto.serializer(), dto)
        localDatasource.saveCache(rawJson, Clock.System.now().toEpochMilliseconds())
        return dto.toModel()
    }

    private suspend fun populateRegistries() {
        val companies = remoteDatasource.getCompanies().map { dto ->
            Company(label = dto.name, iconUrl = BASE_URL + dto.icon)
        }
        Company.register(companies)

        val skills = remoteDatasource.getTechnologies().map { dto ->
            Skill(label = dto.name, iconUrl = BASE_URL + dto.icon, category = dto.category)
        }
        Skill.register(skills)
        registriesPopulated = true
    }

    companion object {
        private const val CACHE_TTL_MS = 15 * 60 * 1000L // 15 minutes
        private const val BASE_URL = "https://api.aymeric.lefeyer.fr"
    }
}
