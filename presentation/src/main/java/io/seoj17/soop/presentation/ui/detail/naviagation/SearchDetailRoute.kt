package io.seoj17.soop.presentation.ui.detail.naviagation

import androidx.compose.runtime.Composable
import io.seoj17.soop.presentation.ui.detail.SearchDetailScreen

@Composable
fun SearchDetailRoute() {
    SearchDetailScreen(
        repoName = "",
        repoLanguage = "",
        userName = "",
        onClickUserDetail = {},
        isBottomSheetVisible = true,
    )
}