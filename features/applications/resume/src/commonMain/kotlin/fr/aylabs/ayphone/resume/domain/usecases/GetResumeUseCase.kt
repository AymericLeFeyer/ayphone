package fr.aylabs.ayphone.resume.domain.usecases

import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.domain.repositories.ResumeRepository

class GetResumeUseCase(val repository: ResumeRepository) {
    suspend operator fun invoke(): Resume {
        return repository.getResumeData()
    }
}