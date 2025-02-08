package io.seoj17.soop.presentation.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.seoj17.soop.domain.usecase.GetRepoListUseCase
import io.seoj17.soop.presentation.base.BaseViewModel
import io.seoj17.soop.presentation.ui.search.model.toUiModel
import io.seoj17.soop.presentation.ui.search.mvi.SearchIntent
import io.seoj17.soop.presentation.ui.search.mvi.SearchSideEffect
import io.seoj17.soop.presentation.ui.search.mvi.SearchUiState
import io.seoj17.soop.presentation.utils.ImmutableList
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRepoListUseCase: GetRepoListUseCase,

) : BaseViewModel<SearchUiState, SearchSideEffect, SearchIntent>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchUiState {
        return SearchUiState.initialize()
    }

    override fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.ClickSearch -> {
                getRepoList(intent.searchText)
            }

            is SearchIntent.ClickSearchResultItem -> {
                postSideEffect(SearchSideEffect.NavigateToSearchDetail)
            }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        reduce {
            copy(isLoading = isLoading)
        }
    }

    private fun getRepoList(repoName: String) {
        viewModelScope.launch {
            getRepoListUseCase(repoName)
                .onStart { updateLoading(true) }
                .onCompletion { updateLoading(false) }
                .map { it.toUiModel() }
                .collect { repoList ->
                    reduce {
                        copy(repoList = ImmutableList(repoList))
                    }
                }
        }
    }
}
