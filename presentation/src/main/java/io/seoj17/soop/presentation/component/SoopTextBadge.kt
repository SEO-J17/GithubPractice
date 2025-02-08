package io.seoj17.soop.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SoopTextBadge(
    modifier: Modifier,
    text: String,
    textSize: TextUnit = 15.sp,
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.LightGray)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        text = text,
        color = Color.Gray,
        fontSize = textSize,
    )
}

@Composable
@Preview
private fun SoopTextBadgePreview() {
    SoopTextBadge(modifier = Modifier, text = "Hello")
}
