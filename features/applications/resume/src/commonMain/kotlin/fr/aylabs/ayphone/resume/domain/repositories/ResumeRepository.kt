package fr.aylabs.ayphone.resume.domain.repositories

import fr.aylabs.ayphone.resume.domain.models.Resume

interface ResumeRepository {
    suspend fun getResumeData(): Resume
}