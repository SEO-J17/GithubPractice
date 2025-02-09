package io.seoj17.soop.presentation.ui.detail.naviagation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.seoj17.soop.presentation.R
import io.seoj17.soop.presentation.ui.detail.SearchDetailScreen
import io.seoj17.soop.presentation.ui.detail.SearchDetailViewModel
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailIntent
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailSideEffect
import io.seoj17.soop.presentation.utils.showToast

@Composable
fun SearchDetailRoute(
    onBackPreviousScreen: () -> Unit,
    viewModel: SearchDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SearchDetailSideEffect.BackPreviousScreen -> {
                    onBackPreviousScreen()
                }

                SearchDetailSideEffect.ShowError -> {
                    context.showToast(R.string.error_message)
                }
            }
        }
    }

    SearchDetailScreen(
        uiState = uiState,
        onClickUserDetail = { viewModel.handleIntent(SearchDetailIntent.ClickMoreUserInfo) },
        onTouchBottomSheetClose = { viewModel.handleIntent(SearchDetailIntent.TouchBottomSheetClose) },
    )
}
