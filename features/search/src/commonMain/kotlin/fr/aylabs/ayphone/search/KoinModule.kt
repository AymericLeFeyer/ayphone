package fr.aylabs.ayphone.search

import fr.aylabs.ayphone.search.domain.usecases.SearchUseCase
import fr.aylabs.ayphone.search.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val SEARCH_MODULE = module {
    factory { SearchUseCase(get()) }
    viewModel { SearchViewModel(get()) }
}
