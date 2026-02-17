package fr.aylabs.ayphone.clients

import fr.aylabs.ayphone.clients.ui.viewmodels.ClientsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val CLIENTS_MODULE = module {
    viewModel {
        ClientsViewModel(
            getResumeUseCase = get(),
        )
    }
}
