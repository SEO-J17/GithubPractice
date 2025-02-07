package io.seoj17.soop.presentation.ui.detail.mvi

import io.seoj17.soop.presentation.base.UiIntent

sealed interface SearchDetailIntent : UiIntent {
    data object ClickMoreUserInfo : SearchDetailIntent
    data object TouchBottomSheetClose : SearchDetailIntent
}
