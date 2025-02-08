package io.seoj17.soop.presentation.ui.search.mvi

import io.seoj17.soop.presentation.base.UiState
import io.seoj17.soop.presentation.ui.search.model.RepoInfo
import io.seoj17.soop.presentation.utils.ImmutableList

data class SearchUiState(
    val isLoading: Boolean,
    val repoList: ImmutableList<RepoInfo>,
) : UiState {
    companion object {
        fun initialize() = SearchUiState(
            isLoading = false,
            repoList = ImmutableList(emptyList()),
        )
    }
}
