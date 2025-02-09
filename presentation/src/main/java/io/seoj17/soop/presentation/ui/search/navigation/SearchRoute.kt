package io.seoj17.soop.presentation.ui.search.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.seoj17.soop.presentation.ui.search.SearchScreen
import io.seoj17.soop.presentation.ui.search.SearchViewModel
import io.seoj17.soop.presentation.ui.search.mvi.SearchIntent
import io.seoj17.soop.presentation.ui.search.mvi.SearchSideEffect

@Composable
fun SearchRoute(
    onClickSearchResultItem: (String, String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SearchSideEffect.NavigateToSearchDetail -> onClickSearchResultItem(
                    sideEffect.userName,
                    sideEffect.repoName,
                )
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
