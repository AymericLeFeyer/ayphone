Imimport fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

// It's temporary, because I'm using this url for testing purpose
expect fun githubResumeUrl(): String

class ResumeRemoteDatasource(val client: HttpClient) {

    suspend fun getResumeData(): ResumeDto {
        val response = client
            .get(githubResumeUrl())

        return when (response.status) {
            HttpStatusCode.OK -> {
                val rawJson = response.body<String>()
                val jsonParser = Json { ignoreUnknownKeys = true }
                jsonParser.decodeFromString(ResumeDto.serializer(), rawJson)
            }

            else -> throw Exception("Error fetching resume data: ${response.status}")
        }
    }
}