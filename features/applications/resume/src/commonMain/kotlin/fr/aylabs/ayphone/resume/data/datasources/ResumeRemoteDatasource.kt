package fr.aylabs.ayphone.resume.data.datasources

import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ResumeRemoteDatasource(private val client: HttpClient) {

    suspend fun getResumeData(): ResumeDto {
        return client.get("https://api.aymeric.lefeyer.fr/api/profile").body()
    }
}
