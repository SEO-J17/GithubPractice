package io.seoj17.soop.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import io.seoj17.soop.presentation.ui.detail.naviagation.searchDetailNavigation
import io.seoj17.soop.presentation.ui.search.navigation.searchNavigation

fun NavGraphBuilder.searchNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = SoopRoute.Search.route,
        route = SoopRoute.Search.route,
    ) {
        searchNavigation()
        searchDetailNavigation()
    }
}