package io.seoj17.soop.presentation.ui.detail.naviagation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.seoj17.soop.presentation.ui.detail.SearchDetailScreen
import io.seoj17.soop.presentation.ui.detail.SearchDetailViewModel
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailIntent

@Composable
fun SearchDetailRoute(
    viewModel: SearchDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchDetailScreen(
        uiState = uiState,
        onClickUserDetail = { viewModel.handleIntent(SearchDetailIntent.ClickMoreUserInfo) },
        onTouchBottomSheetClose = { viewModel.handleIntent(SearchDetailIntent.TouchBottomSheetClose) },
    )
}
