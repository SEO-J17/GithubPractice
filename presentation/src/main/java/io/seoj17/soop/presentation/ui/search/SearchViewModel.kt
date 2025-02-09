package io.seoj17.soop.presentation.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.seoj17.soop.domain.usecase.GetRepoListUseCase
import io.seoj17.soop.presentation.base.BaseViewModel
import io.seoj17.soop.presentation.ui.search.model.RepoInfo
import io.seoj17.soop.presentation.ui.search.model.toUiModel
import io.seoj17.soop.presentation.ui.search.mvi.SearchIntent
import io.seoj17.soop.presentation.ui.search.mvi.SearchSideEffect
import io.seoj17.soop.presentation.ui.search.mvi.SearchUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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
                postSideEffect(
                    SearchSideEffect.NavigateToSearchDetail(
                        userName = intent.userName,
                        repoName = intent.repoName,
                    ),
                )
            }

            is SearchIntent.LoadData -> {
                updateLoading(intent.isLoading)
            }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        reduce {
            copy(isLoading = isLoading)
        }
    }

    private fun getRepoList(repoName: String) {
        val flowPaging = getRepoListUseCase(repoName)
            .map { pagingData ->
                pagingData.map {
                    it.toUiModel()
                }
            }
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
        updateSearchResult(flowPaging)
    }

    private fun updateSearchResult(searchResult: Flow<PagingData<RepoInfo>>) {
        reduce {
            copy(repoPagingData = searchResult)
        }
    }
}
