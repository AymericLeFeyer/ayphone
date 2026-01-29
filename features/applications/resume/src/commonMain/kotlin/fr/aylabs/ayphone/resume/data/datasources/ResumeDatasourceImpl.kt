import fr.aylabs.ayphone.resume.data.datasources.ResumeDatasource
import fr.aylabs.network.Client
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode


class ResumeDatasourceImpl : ResumeDatasource {
    override suspend fun getResumeData(): Any {
        val client = Client().client

        return client.get("https://corsproxy.io/?https://raw.githubusercontent.com/AymericLeFeyer/timelife/refs/heads/main/src/data/profile.json")
            .let {
                when (it.status) {
                    HttpStatusCode.OK ->
                        return it.body<String>()
                }
            }
    }
}