package io.seoj17.soop.presentation.ui.search.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.seoj17.soop.presentation.R
import io.seoj17.soop.presentation.ui.search.SearchScreen
import io.seoj17.soop.presentation.ui.search.SearchViewModel
import io.seoj17.soop.presentation.ui.search.mvi.SearchIntent
import io.seoj17.soop.presentation.ui.search.mvi.SearchSideEffect
import io.seoj17.soop.presentation.utils.showToast

@Composable
fun SearchRoute(
    onClickSearchResultItem: (String, String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SearchSideEffect.NavigateToSearchDetail -> onClickSearchResultItem(
                    sideEffect.userName,
                    sideEffect.repoName,
                )

                SearchSideEffect.ShowError -> context.showToast(R.string.error_message)
            }
        }
    }

    SearchScreen(
        uiState = uiState,
        onClickSearch = { searchText ->
            viewModel.handleIntent(SearchIntent.ClickSearch(searchText))
        },
        onClickSearchResultItem = { userName, repoName ->
            viewModel.handleIntent(
                SearchIntent.ClickSearchResultItem(
                    userName = userName,
                    repoName = repoName,
                ),
            )
        },
        onDataLoading = { isLoading ->
            viewModel.handleIntent(SearchIntent.LoadData(isLoading))
        },
    )
}
