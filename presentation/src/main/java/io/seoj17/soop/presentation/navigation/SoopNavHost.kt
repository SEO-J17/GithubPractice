package io.seoj17.soop.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SoopNavHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SoopRoute.startRoute,
        exitTransition = { ExitTransition.None },
        enterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
    ) {
        searchNavGraph(navController = navController)
    }
}