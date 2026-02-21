package fr.aylabs.ayphone.aylabs.domain.usecases

import fr.aylabs.ayphone.aylabs.domain.models.AyLabsInfo
import fr.aylabs.ayphone.aylabs.domain.repositories.AyLabsRepository

class GetAyLabsInfoUseCase(private val repository: AyLabsRepository) {
    suspend operator fun invoke(): AyLabsInfo = repository.getInfo()
}
