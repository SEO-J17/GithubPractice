package io.seoj17.soop.presentation.ui.detail.naviagation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.seoj17.soop.presentation.navigation.SoopRoute
import io.seoj17.soop.presentation.navigation.SoopRoute.Search.KEY_REPO_NAME
import io.seoj17.soop.presentation.navigation.SoopRoute.Search.KEY_USER_NAME

fun NavController.navigateToSearchDetail(userName: String, repoName: String) {
    navigate("${SoopRoute.SearchDetail.route}/$userName/$repoName")
}

fun NavGraphBuilder.searchDetailNavigation() {
    composable(
        route = "${SoopRoute.SearchDetail.route}/{$KEY_USER_NAME}/{$KEY_REPO_NAME}",
        arguments = listOf(
            navArgument(KEY_USER_NAME) { type = NavType.StringType },
            navArgument(KEY_REPO_NAME) { type = NavType.StringType },
        ),
    ) {
        SearchDetailRoute()
    }
}
