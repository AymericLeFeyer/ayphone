package fr.aylabs.ayphone.about

import fr.aylabs.ayphone.about.ui.viewmodels.AboutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ABOUT_MODULE = module {
    viewModel {
        AboutViewModel(
            getResumeUseCase = get(),
        )
    }
}
