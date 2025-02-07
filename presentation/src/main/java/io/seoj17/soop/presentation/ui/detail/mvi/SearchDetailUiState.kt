package io.seoj17.soop.presentation.ui.detail.mvi

import io.seoj17.soop.presentation.base.UiState

data class SearchDetailUiState(
    val isLoading: Boolean,
    val isBottomSheetVisible: Boolean,
) : UiState {
    companion object {
        fun initialize() = SearchDetailUiState(
            isLoading = false,
            isBottomSheetVisible = false,
        )
    }
}
