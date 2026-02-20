package fr.aylabs.ayphone.frame.interfaces.ui

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val FRAME_MODULE = module {
    viewModel {
        FrameViewModel(
            getResumeUseCase = get(),
            installationRepository = get()
        )
    }
}
