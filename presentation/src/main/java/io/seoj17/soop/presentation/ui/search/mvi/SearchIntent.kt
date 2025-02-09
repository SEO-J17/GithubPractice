package io.seoj17.soop.presentation.ui.search.mvi

import io.seoj17.soop.presentation.base.UiIntent

sealed interface SearchIntent : UiIntent {
    data class ClickSearch(val searchText: String) : SearchIntent
    data class ClickSearchResultItem(val userName: String, val repoName: String) : SearchIntent
    data class LoadData(val isLoading: Boolean) : SearchIntent
}
