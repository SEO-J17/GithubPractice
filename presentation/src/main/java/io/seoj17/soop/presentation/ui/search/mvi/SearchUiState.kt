package io.seoj17.soop.presentation.ui.search.mvi

import androidx.paging.PagingData
import io.seoj17.soop.presentation.base.UiState
import io.seoj17.soop.presentation.ui.search.model.RepoInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchUiState(
    val isLoading: Boolean,
    val repoPagingData: Flow<PagingData<RepoInfo>>,
) : UiState {
    companion object {
        fun initialize() = SearchUiState(
            isLoading = false,
            repoPagingData = emptyFlow(),
        )
    }
}
