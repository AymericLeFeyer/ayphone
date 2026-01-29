package fr.aylabs.ayphone.resume.data.repositories

import ResumeRemoteDatasource
import fr.aylabs.ayphone.resume.data.mappers.toModel
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.repositories.ResumeRepository

class ResumeRepositoryImpl(
    private val remoteDatasource: ResumeRemoteDatasource
) : ResumeRepository {
    override suspend fun getResumeData(): Resume {
        return remoteDatasource.getResumeData().toModel()
    }
}