package io.seoj17.soop.presentation.navigation

sealed interface SoopRoute {
    val route: String

    data object Search : SoopRoute {
        override val route: String = "search"
    }

    data object SearchDetail : SoopRoute {
        override val route: String = "searchDetail"
    }

    companion object {
        val startRoute = Search.route
    }
}