package fr.aylabs.ayphone.aylabs

import fr.aylabs.ayphone.aylabs.data.datasources.AyLabsDatasource
import fr.aylabs.ayphone.aylabs.data.repositories.AyLabsRepositoryImpl
import fr.aylabs.ayphone.aylabs.domain.repositories.AyLabsRepository
import fr.aylabs.ayphone.aylabs.domain.usecases.GetAyLabsInfoUseCase
import fr.aylabs.ayphone.aylabs.ui.viewmodels.AyLabsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AYLABS_MODULE = module {
    single { AyLabsDatasource(client = get()) }
    single<AyLabsRepository> { AyLabsRepositoryImpl(datasource = get()) }
    single { GetAyLabsInfoUseCase(repository = get()) }
    viewModel { AyLabsViewModel(getAyLabsInfoUseCase = get()) }
}
