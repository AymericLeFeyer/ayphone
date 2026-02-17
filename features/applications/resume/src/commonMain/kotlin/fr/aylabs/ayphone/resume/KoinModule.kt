package fr.aylabs.ayphone.resume

import ResumeRemoteDatasource
import fr.aylabs.ayphone.resume.data.datasources.ResumeLocalDatasource
import fr.aylabs.ayphone.resume.data.repositories.ResumeRepositoryImpl
import fr.aylabs.ayphone.resume.domain.repositories.ResumeRepository
import fr.aylabs.ayphone.resume.domain.usecases.GetDarkModeUseCase
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase
import fr.aylabs.ayphone.resume.domain.usecases.SetDarkModeUseCase
import org.koin.dsl.module

val RESUME_MODULE = module {
    // Data
    single { ResumeRemoteDatasource(client = get()) }
    single { ResumeLocalDatasource(settings = get()) }
    single<ResumeRepository> { ResumeRepositoryImpl(remoteDatasource = get(), localDatasource = get()) }

    // Domain
    single { GetResumeUseCase(repository = get()) }
    single { GetDarkModeUseCase(getSettingUseCase = get()) }
    single { SetDarkModeUseCase(setSettingUseCase = get()) }
}
