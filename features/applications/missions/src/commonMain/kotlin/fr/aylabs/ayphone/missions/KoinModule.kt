package fr.aylabs.ayphone.missions

import fr.aylabs.ayphone.missions.ui.viewmodels.MissionsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val MISSIONS_MODULE = module {
    viewModel {
        MissionsViewModel(
            getResumeUseCase = get(),
        )
    }
}
