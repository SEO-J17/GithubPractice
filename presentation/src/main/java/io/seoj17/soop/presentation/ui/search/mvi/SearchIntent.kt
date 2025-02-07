package io.seoj17.soop.presentation.ui.search.mvi

import io.seoj17.soop.presentation.base.UiIntent

sealed interface SearchIntent : UiIntent {
    data class ClickSearch(val searchText: String) : SearchIntent
    data object ClickSearchResultItem : SearchIntent
}
