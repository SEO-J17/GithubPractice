package io.seoj17.soop.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.seoj17.soop.presentation.R

@Composable
fun SoopUserContainer(
    modifier: Modifier,
    userThumbnailUrl: String,
    userName: String,
    thumbnailSize: Dp = 30.dp,
    nameColor: Color = Color.Gray,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(thumbnailSize),
            model = userThumbnailUrl,
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(R.string.user_thumbnail),
        )
        Text(
            text = userName,
            color = nameColor,
        )
    }
}

@Composable
@Preview
private fun SoopUserContainerPreview() {
    SoopUserContainer(
        modifier = Modifier,
        userThumbnailUrl = "https://avatars.githubusercontent.com/u/48726953?v=4",
        userName = "seoj17",
    )
}
