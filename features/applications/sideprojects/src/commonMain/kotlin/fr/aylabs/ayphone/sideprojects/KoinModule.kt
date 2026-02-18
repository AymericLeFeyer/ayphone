package fr.aylabs.ayphone.sideprojects

import fr.aylabs.ayphone.sideprojects.ui.viewmodels.SideProjectsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val SIDE_PROJECTS_MODULE = module {
    viewModel {
        SideProjectsViewModel(
            getResumeUseCase = get(),
        )
    }
}
