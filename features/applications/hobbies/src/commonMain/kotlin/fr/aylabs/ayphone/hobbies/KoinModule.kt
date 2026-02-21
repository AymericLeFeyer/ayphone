package fr.aylabs.ayphone.hobbies

import fr.aylabs.ayphone.hobbies.data.repositories.HobbiesRepositoryImpl
import fr.aylabs.ayphone.hobbies.domain.repositories.HobbiesRepository
import fr.aylabs.ayphone.hobbies.domain.usecases.GetHobbiesDataUseCase
import fr.aylabs.ayphone.hobbies.ui.viewmodels.HobbiesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val HOBBIES_MODULE = module {
    single<HobbiesRepository> { HobbiesRepositoryImpl() }
    single { GetHobbiesDataUseCase(repository = get()) }
    viewModel { HobbiesViewModel(getHobbiesDataUseCase = get()) }
}
