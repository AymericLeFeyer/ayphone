import fr.aylabs.ayphone.resume.data.dtos.ResumeDto
import fr.aylabs.network.Client
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

// It's temporary, because I'm using this url for testing purpose
expect fun githubResumeUrl(): String

class ResumeRemoteDatasource {

    suspend fun getResumeData(): ResumeDto {
        val client = Client().client
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