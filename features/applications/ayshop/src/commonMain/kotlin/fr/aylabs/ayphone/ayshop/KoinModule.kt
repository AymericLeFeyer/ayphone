package fr.aylabs.ayphone.ayshop

import fr.aylabs.ayphone.ayshop.domain.InstallationRepository
import fr.aylabs.ayphone.ayshop.ui.viewmodels.AyShopViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AYSHOP_MODULE = module {
    single {
        InstallationRepository(
            getSettingUseCase = get(),
            setSettingUseCase = get(),
        )
    }

    viewModel {
        AyShopViewModel(
            installationRepository = get(),
        )
    }
}
