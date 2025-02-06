package io.seoj17.soop.presentation.ui.search.navigation

import androidx.compose.runtime.Composable
import io.seoj17.soop.presentation.ui.search.SearchScreen
import io.seoj17.soop.presentation.utils.ImmutableList

@Composable
fun SearchRoute() {
    SearchScreen(
        onClickSearch = { searchText ->

        },
        repoList = ImmutableList(emptyList()),
    )
}