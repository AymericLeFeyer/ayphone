package fr.aylabs.ayphone.stack

import fr.aylabs.ayphone.stack.ui.viewmodels.StackViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val STACK_MODULE = module {
    viewModel {
        StackViewModel(
            getResumeUseCase = get(),
        )
    }
}
