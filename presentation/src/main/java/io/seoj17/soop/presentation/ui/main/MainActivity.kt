package io.seoj17.soop.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.seoj17.soop.presentation.navigation.SoopNavHost
import io.seoj17.soop.presentation.theme.SoopAssignmentTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            SoopAssignmentTheme {
                SoopNavHost(
                    modifier = Modifier,
                    navController = navHostController,
                )
            }
        }
    }
}
