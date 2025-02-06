package io.seoj17.soop.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.seoj17.soop.presentation.R
import io.seoj17.soop.presentation.component.SoopHorizontalDivider
import io.seoj17.soop.presentation.component.SoopTextBadge
import io.seoj17.soop.presentation.component.SoopUserContainer
import io.seoj17.soop.presentation.utils.rippleClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDetailScreen(
    repoName: String,
    repoLanguage: String,
    userName: String,
    onClickUserDetail: () -> Unit,
    isBottomSheetVisible: Boolean,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    if (isBottomSheetVisible) {
        UserInfoBottomSheet(
            modifier = Modifier.wrapContentSize(),
            sheetState = sheetState,
        )
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 10.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = repoName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        InfoBadgeContainer(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp),
            repoName = repoName,
            repoLanguage = repoLanguage,
            userName = userName,
        )
        SoopHorizontalDivider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
        )
        RepoAttributesContainer(
            modifier = Modifier.fillMaxWidth(),
            starCount = "100",
            watcherCount = "100",
            forkCount = "100",
        )
        SoopHorizontalDivider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
        )
        UserInfoContainer(
            modifier = Modifier.fillMaxWidth(),
            onClickUserDetail = onClickUserDetail,
        )
        SoopHorizontalDivider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
        )
        DescContainer(modifier = Modifier.fillMaxWidth(), repoDesc = "This is description")
    }
}

@Composable
private fun DescContainer(modifier: Modifier, repoDesc: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = stringResource(R.string.detail_description_title), fontWeight = FontWeight.Bold)
        Text(text = repoDesc, color = Color.Gray)
    }
}

@Composable
private fun UserInfoContainer(modifier: Modifier, onClickUserDetail: () -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SoopUserContainer(
            modifier = Modifier,
            userName = "seoj17",
            userThumbnailUrl = "https://avatars.githubusercontent.com/u/48755115?v=4",
            thumbnailSize = 40.dp,
        )
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .rippleClick { onClickUserDetail() }
                .background(color = Color.Blue.copy(alpha = 0.9f))
                .padding(horizontal = 15.dp, vertical = 10.dp),
            text = stringResource(R.string.detail_more_label),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun InfoBadgeContainer(
    modifier: Modifier,
    repoName: String,
    repoLanguage: String,
    userName: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        SoopTextBadge(modifier = Modifier, text = repoName, textSize = 12.sp)
        SoopTextBadge(modifier = Modifier, text = repoLanguage, textSize = 12.sp)
        SoopTextBadge(modifier = Modifier, text = userName, textSize = 12.sp)
    }
}

@Composable
private fun RepoAttributesContainer(
    modifier: Modifier,
    starCount: String,
    watcherCount: String,
    forkCount: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        RepoAttribute(
            modifier = Modifier,
            title = stringResource(R.string.detail_star_title),
            content = starCount,
        )
        RepoAttribute(
            modifier = Modifier,
            title = stringResource(R.string.detail_watcher_title),
            content = watcherCount,
        )
        RepoAttribute(
            modifier = Modifier,
            title = stringResource(R.string.detail_fork_title),
            content = forkCount,
        )
    }
}

@Composable
private fun RepoAttribute(
    modifier: Modifier,
    title: String,
    content: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = content, color = Color.Gray)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserInfoBottomSheet(modifier: Modifier, sheetState: SheetState) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { },
        sheetState = sheetState,
        dragHandle = null,
        containerColor = Color.White,
    ) {
        BottomSheetContainer(
            modifier = Modifier
                .padding(20.dp)
                .wrapContentSize(),
        )
    }
}

@Composable
private fun BottomSheetContainer(modifier: Modifier) {
    // TODO: Replace with real data
    val userInfoList = listOf(
        Pair(R.string.sheet_followers_label, "100"),
        Pair(R.string.sheet_following_label, "100"),
        Pair(R.string.sheet_language_label, "100"),
        Pair(R.string.sheet_repositories_label, "Kotlin"),
        Pair(R.string.sheet_bio_label, "soop"),
    )

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(20.dp)) {
        SoopUserContainer(
            modifier = Modifier,
            userName = "seoj17",
            userThumbnailUrl = "https://avatars.githubusercontent.com/u/48755115?v=4",
            thumbnailSize = 40.dp,
            nameColor = Color.Black,
        )
        userInfoList.forEach { (titleResId, content) ->
            UserInfoContent(
                modifier = Modifier,
                titleResId = titleResId,
                content = content,
            )
        }
    }
}

@Composable
private fun UserInfoContent(modifier: Modifier, titleResId: Int, content: String) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = stringResource(titleResId), color = Color.Black)
        Text(text = content, color = Color.Gray)
    }
}

@Composable
@Preview
private fun SearchDetailScreenPreview() {
    SearchDetailScreen(
        repoName = "soop",
        repoLanguage = "Kotlin",
        userName = "seoj17",
        onClickUserDetail = {},
        isBottomSheetVisible = true,
    )
}
