package fr.aylabs.ayphone.resume

import ResumeRemoteDatasource
import fr.aylabs.ayphone.resume.data.repositories.ResumeRepositoryImpl
import fr.aylabs.ayphone.resume.domain.repositories.ResumeRepository
import fr.aylabs.ayphone.resume.domain.usecases.GetDarkModeUseCase
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.resume.domain.usecases.SetDarkModeUseCase
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val RESUME_MODULE = module {
    // Data
    single { ResumeRemoteDatasource(client = get()) }
    single<ResumeRepository> { ResumeRepositoryImpl(remoteDatasource = get()) }

    // Domain
    single { GetResumeUseCase(repository = get()) }
    single { GetDarkModeUseCase(getSettingUseCase = get()) }
    single { SetDarkModeUseCase(setSettingUseCase = get()) }

    // UI
    viewModel {
        ResumeViewModel(
            getResumeUseCase = get(),
        )
    }
}