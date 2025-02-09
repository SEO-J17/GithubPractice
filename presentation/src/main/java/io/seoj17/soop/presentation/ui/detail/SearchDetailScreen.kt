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
import io.seoj17.soop.presentation.component.SoopLoadingCircular
import io.seoj17.soop.presentation.component.SoopTextBadge
import io.seoj17.soop.presentation.component.SoopUserContainer
import io.seoj17.soop.presentation.ui.detail.model.UserDetail
import io.seoj17.soop.presentation.ui.detail.mvi.SearchDetailUiState
import io.seoj17.soop.presentation.utils.NumberFormater
import io.seoj17.soop.presentation.utils.rippleClick

@Composable
fun SearchDetailScreen(
    uiState: SearchDetailUiState,
    onClickUserDetail: () -> Unit,
    onTouchBottomSheetClose: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 10.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = uiState.repoDetail.repoName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        InfoBadgeContainer(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp),
            repoName = uiState.repoDetail.repoName,
            repoLanguage = uiState.repoDetail.mainLanguage.orEmpty(),
            userName = uiState.userDetail.userName,
        )
        SoopHorizontalDivider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
        )
        RepoAttributesContainer(
            modifier = Modifier.fillMaxWidth(),
            starCount = uiState.repoDetail.starCount,
            watcherCount = uiState.repoDetail.watcherCount,
            forkCount = uiState.repoDetail.forkCount,
        )
        SoopHorizontalDivider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
        )
        UserInfoContainer(
            modifier = Modifier.fillMaxWidth(),
            userThumbnailUrl = uiState.userDetail.userThumbnailUrl,
            userName = uiState.userDetail.userName,
            onClickUserDetail = onClickUserDetail,
        )
        SoopHorizontalDivider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
        )
        DescContainer(
            modifier = Modifier.fillMaxWidth(),
            repoDesc = uiState.repoDetail.description.orEmpty(),
        )
    }

    if (uiState.isBottomSheetVisible) {
        UserInfoBottomSheet(
            modifier = Modifier.wrapContentSize(),
            userDetail = uiState.userDetail,
            onTouchBottomSheetClose = onTouchBottomSheetClose,
        )
    }

    if (uiState.isLoading) {
        SoopLoadingCircular(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun DescContainer(modifier: Modifier, repoDesc: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = stringResource(R.string.detail_description_title), fontWeight = FontWeight.Bold)
        if (repoDesc.isNotEmpty()) {
            Text(text = repoDesc, color = Color.Gray)
        }
    }
}

@Composable
private fun UserInfoContainer(
    modifier: Modifier,
    userThumbnailUrl: String,
    userName: String,
    onClickUserDetail: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SoopUserContainer(
            modifier = Modifier,
            userName = userName,
            userThumbnailUrl = userThumbnailUrl,
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
        if (repoLanguage.isNotEmpty()) {
            SoopTextBadge(modifier = Modifier, text = repoLanguage, textSize = 12.sp)
        }
        SoopTextBadge(modifier = Modifier, text = userName, textSize = 12.sp)
    }
}

@Composable
private fun RepoAttributesContainer(
    modifier: Modifier,
    starCount: Int,
    watcherCount: Int,
    forkCount: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        RepoAttribute(
            modifier = Modifier,
            title = stringResource(R.string.detail_star_title),
            content = NumberFormater.format(starCount),
        )
        RepoAttribute(
            modifier = Modifier,
            title = stringResource(R.string.detail_watcher_title),
            content = NumberFormater.format(watcherCount),
        )
        RepoAttribute(
            modifier = Modifier,
            title = stringResource(R.string.detail_fork_title),
            content = NumberFormater.format(forkCount),
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
private fun UserInfoBottomSheet(
    modifier: Modifier,
    onTouchBottomSheetClose: () -> Unit,
    userDetail: UserDetail,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { onTouchBottomSheetClose() },
        sheetState = sheetState,
        dragHandle = null,
        containerColor = Color.White,
    ) {
        BottomSheetContainer(
            modifier = Modifier
                .padding(20.dp)
                .wrapContentSize(),
            userDetail = userDetail,
        )
    }
}

@Composable
private fun BottomSheetContainer(modifier: Modifier, userDetail: UserDetail) {
    val userInfoList = listOf(
        Pair(R.string.sheet_followers_label, NumberFormater.format(userDetail.followerCount)),
        Pair(R.string.sheet_following_label, NumberFormater.format(userDetail.followingCount)),
        Pair(R.string.sheet_language_label, userDetail.usedLanguage),
        Pair(R.string.sheet_repositories_label, NumberFormater.format(userDetail.repoCount)),
        Pair(R.string.sheet_bio_label, userDetail.bio.orEmpty()),
    )

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(20.dp)) {
        SoopUserContainer(
            modifier = Modifier,
            userName = userDetail.userName,
            userThumbnailUrl = userDetail.userThumbnailUrl,
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
        uiState = SearchDetailUiState.initialize(),
        onClickUserDetail = {},
        onTouchBottomSheetClose = {},
    )
}
