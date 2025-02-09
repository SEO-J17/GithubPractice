package io.seoj17.soop.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SoopLoadingCircular(modifier: Modifier) {
    Box(modifier = modifier.background(Color.White), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Blue,
        )
    }
}

@Composable
@Preview
private fun SoopLoadingCircularPreview() {
    SoopLoadingCircular(Modifier.fillMaxSize())
}