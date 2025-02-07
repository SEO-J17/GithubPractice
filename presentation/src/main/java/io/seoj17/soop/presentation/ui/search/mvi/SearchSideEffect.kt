package io.seoj17.soop.presentation.ui.search.mvi

import io.seoj17.soop.presentation.base.UiSideEffect

sealed interface SearchSideEffect : UiSideEffect {
    data object NavigateToSearchDetail : SearchSideEffect
}
