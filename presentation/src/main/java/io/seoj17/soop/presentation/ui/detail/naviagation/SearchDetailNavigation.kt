package io.seoj17.soop.presentation.ui.detail.naviagation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.seoj17.soop.presentation.navigation.SoopRoute

fun NavController.navigateToSearchDetail() {
    navigate(
        SoopRoute.SearchDetail.route,
    )
}

fun NavGraphBuilder.searchDetailNavigation() {
    composable(route = SoopRoute.SearchDetail.route) {
        SearchDetailRoute()
    }
}
