package fr.aylabs.ayphone.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.aylabs.ayphone.search.domain.models.SearchResult
import fr.aylabs.ayphone.search.domain.usecases.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _results = MutableStateFlow<List<SearchResult>>(emptyList())
    val results: StateFlow<List<SearchResult>> = _results.asStateFlow()

    init {
        viewModelScope.launch {
            _query.debounce(200L).collectLatest { q ->
                _results.value = if (q.isBlank()) emptyList() else searchUseCase(q)
            }
        }
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun clear() {
        _query.value = ""
    }
}
