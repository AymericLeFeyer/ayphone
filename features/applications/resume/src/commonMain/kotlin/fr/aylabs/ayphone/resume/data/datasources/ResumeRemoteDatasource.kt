package fr.aylabs.ayphone.resume.data.datasources

import fr.aylabs.ayphone.resume.data.dtos.CompanyIconDto
import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import fr.aylabs.ayphone.resume.data.dtos.TechnologyIconDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ResumeRemoteDatasource(private val client: HttpClient) {

    suspend fun getResumeData(): ResumeDto {
        return client.get("$BASE_URL/api/profile").body()
    }

    suspend fun getCompanies(): List<CompanyIconDto> {
        return client.get("$BASE_URL/api/companies").body()
    }

    suspend fun getTechnologies(): List<TechnologyIconDto> {
        return client.get("$BASE_URL/api/technologies").body()
    }

    companion object {
        private const val BASE_URL = "https://api.aymeric.lefeyer.fr"
    }
}
