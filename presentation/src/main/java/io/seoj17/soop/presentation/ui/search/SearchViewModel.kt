package io.seoj17.soop.presentation.ui.search

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import io.seoj17.soop.presentation.base.BaseViewModel
import io.seoj17.soop.presentation.ui.search.mvi.SearchIntent
import io.seoj17.soop.presentation.ui.search.mvi.SearchSideEffect
import io.seoj17.soop.presentation.ui.search.mvi.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SearchUiState, SearchSideEffect, SearchIntent>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchUiState {
        return SearchUiState.initialize()
    }

    override fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.ClickSearch -> {
                // TODO : Implement search logic
            }

            is SearchIntent.ClickSearchResultItem -> {
                postSideEffect(SearchSideEffect.NavigateToSearchDetail)
            }
        }
    }
}
