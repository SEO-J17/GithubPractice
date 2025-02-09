package io.seoj17.soop.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.seoj17.soop.presentation.R

@Composable
fun SoopStar(modifier: Modifier, text: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Default.Star,
            contentDescription = stringResource(R.string.start_num),
            tint = Color.Yellow,
        )
        Text(
            text = text,
            color = Color.LightGray,
        )
    }
}

@Composable
@Preview
private fun SoopStarPreview() {
    SoopStar(
        modifier = Modifier,
        text = "12.5k",
    )
}
