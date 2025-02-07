package io.seoj17.soop.presentation.ui.search.mvi

import io.seoj17.soop.presentation.base.UiState

data class SearchUiState(
    val isLoading: Boolean,
) : UiState {
    companion object {
        fun initialize() = SearchUiState(isLoading = false)
    }
}
