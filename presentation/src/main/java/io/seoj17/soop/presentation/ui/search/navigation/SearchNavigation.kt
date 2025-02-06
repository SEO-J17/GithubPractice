package io.seoj17.soop.presentation.ui.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.seoj17.soop.presentation.navigation.SoopRoute

fun NavController.navigateSearch() {
    navigate(
        SoopRoute.Search.route,
    )
}

fun NavGraphBuilder.searchNavigation() {
    composable(route = SoopRoute.Search.route) {
        SearchRoute()
    }
}