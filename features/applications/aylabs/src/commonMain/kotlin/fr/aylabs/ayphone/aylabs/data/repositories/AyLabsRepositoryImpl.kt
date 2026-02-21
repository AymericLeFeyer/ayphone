package fr.aylabs.ayphone.aylabs.data.repositories

import fr.aylabs.ayphone.aylabs.data.datasources.AyLabsDatasource
import fr.aylabs.ayphone.aylabs.data.mappers.mapToAyLabsInfo
import fr.aylabs.ayphone.aylabs.domain.models.AyLabsInfo
import fr.aylabs.ayphone.aylabs.domain.repositories.AyLabsRepository

class AyLabsRepositoryImpl(private val datasource: AyLabsDatasource) : AyLabsRepository {
    override suspend fun getInfo(): AyLabsInfo {
        return mapToAyLabsInfo(datasource.getStats())
    }
}
