package fr.aylabs.ayphone.resume.data.datasources

import ayphone.features.applications.resume.generated.resources.Res
import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class ResumeRemoteDatasource {

    @OptIn(ExperimentalResourceApi::class)
    suspend fun getResumeData(): ResumeDto {
        val rawJson = Res.readBytes("files/profile.json").decodeToString()
        val jsonParser = Json { ignoreUnknownKeys = true }
        return jsonParser.decodeFromString(ResumeDto.serializer(), rawJson)
    }
}
