package io.seoj17.soop.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import io.seoj17.soop.presentation.base.BaseViewModel
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailIntent
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailSideEffect
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailUiState
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SearchDetailUiState, SearchDetailSideEffect, SearchDetailIntent>(savedStateHandle) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): SearchDetailUiState {
        return SearchDetailUiState.initialize()
    }

    override fun handleIntent(intent: SearchDetailIntent) {
        when (intent) {
            is SearchDetailIntent.ClickMoreUserInfo -> {
                updateBottomSheetVisible(true)
            }

            is SearchDetailIntent.TouchBottomSheetClose -> {
                updateBottomSheetVisible(false)
            }
        }
    }

    private fun updateBottomSheetVisible(visible: Boolean) {
        reduce {
            copy(isBottomSheetVisible = visible)
        }
    }
}
