package fr.aylabs.ayphone.resume.data.datasources

interface ResumeDatasource {
    suspend fun getResumeData(): Any
}