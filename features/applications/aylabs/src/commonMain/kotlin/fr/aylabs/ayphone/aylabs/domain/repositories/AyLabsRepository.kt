package fr.aylabs.ayphone.aylabs.domain.repositories

import fr.aylabs.ayphone.aylabs.domain.models.AyLabsInfo

interface AyLabsRepository {
    suspend fun getInfo(): AyLabsInfo
}
