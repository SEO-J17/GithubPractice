package io.seoj17.soop.presentation.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.seoj17.soop.presentation.component.SoopLoadingCircular
import io.seoj17.soop.presentation.component.SoopMainLanguage
import io.seoj17.soop.presentation.component.SoopStar
import io.seoj17.soop.presentation.component.SoopUserContainer
import io.seoj17.soop.presentation.ui.search.component.SearchTextField
import io.seoj17.soop.presentation.ui.search.model.RepoInfo
import io.seoj17.soop.presentation.ui.search.mvi.SearchUiState
import io.seoj17.soop.presentation.utils.ImmutableList
import io.seoj17.soop.presentation.utils.NumberFormater
import io.seoj17.soop.presentation.utils.noRippleClick
import io.seoj17.soop.presentation.utils.noRippleSingleClick

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onClickSearch: (String) -> Unit,
    onClickSearchResultItem: (String, String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchContainer(
            modifier = Modifier.fillMaxWidth(),
            onClickSearch = onClickSearch,
        )
        if (uiState.isLoading) {
            SoopLoadingCircular(modifier = Modifier.fillMaxSize())
        } else {
            SearchResultContainer(
                modifier = Modifier.fillMaxSize(),
                repoList = uiState.repoList,
                onClickSearchResultItem = onClickSearchResultItem,
            )
        }
    }
}

@Composable
private fun SearchResultContainer(
    modifier: Modifier,
    repoList: ImmutableList<RepoInfo>,
    onClickSearchResultItem: (String, String) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(repoList) { index, repoInfo ->
            RepoInfoItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .noRippleClick { onClickSearchResultItem(repoInfo.userName, repoInfo.repoName) }
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = if (index == 0) 20.dp else 13.dp,
                        bottom = if (index == repoList.lastIndex) 20.dp else 0.dp,
                    ),
                repoInfo = repoInfo,
            )
            if (index != repoList.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth(),
                    color = Color.LightGray,
                    thickness = 1.dp,
                )
            }
        }
    }
}

@Composable
private fun RepoInfoItem(modifier: Modifier, repoInfo: RepoInfo) {
    Column(modifier = modifier) {
        SoopUserContainer(
            modifier = Modifier.wrapContentSize(),
            userThumbnailUrl = repoInfo.userThumbnailUrl,
            userName = repoInfo.userName,
        )
        Text(modifier = Modifier.padding(top = 5.dp), text = repoInfo.repoName)
        repoInfo.repoDescription?.let { description ->
            Text(modifier = Modifier.padding(top = 5.dp), text = description)
        }
        RepoPropertyContainer(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp),
            starCount = repoInfo.starCount,
            mainLanguage = repoInfo.usedLanguage,
        )
    }
}

@Composable
private fun SearchContainer(modifier: Modifier, onClickSearch: (String) -> Unit) {
    val searchTextState = rememberTextFieldState()

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        SearchTextField(
            modifier = Modifier.weight(9f),
            textFieldState = searchTextState,
            textHint = "검색어를 입력해주세요",
        )
        Icon(
            modifier = Modifier
                .noRippleSingleClick { onClickSearch(searchTextState.text.toString()) }
                .size(30.dp)
                .weight(1f),
            imageVector = Icons.Default.Search,
            contentDescription = "검색",
        )
    }
}

@Composable
private fun RepoPropertyContainer(modifier: Modifier, starCount: Int, mainLanguage: String?) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SoopStar(
            modifier = Modifier.wrapContentSize(),
            text = NumberFormater.format(starCount),
        )
        SoopMainLanguage(
            modifier = Modifier.wrapContentSize(),
            text = mainLanguage.orEmpty(),
        )
    }
}

@Composable
@Preview
private fun SearchScreenPreview() {
    SearchScreen(
        onClickSearch = {},
        onClickSearchResultItem = { _, _ -> },
        uiState = SearchUiState(
            isLoading = false,
            repoList = ImmutableList(
                listOf(
                    RepoInfo(
                        userThumbnailUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                        userName = "mojombo",
                        repoName = "grit",
                        repoDescription = "Grit is no longer maintained. Check out libgit2/rugged.",
                        starCount = 1825,
                        usedLanguage = "Ruby",
                    ),
                ),
            ),
        ),
    )
}
