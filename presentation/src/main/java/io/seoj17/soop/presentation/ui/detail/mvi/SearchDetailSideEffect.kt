package io.seoj17.soop.presentation.ui.detail.mvi

import io.seoj17.soop.presentation.base.UiSideEffect

sealed interface SearchDetailSideEffect : UiSideEffect {
    data object BackPreviousScreen : SearchDetailSideEffect
    data object ShowError : SearchDetailSideEffect
}
