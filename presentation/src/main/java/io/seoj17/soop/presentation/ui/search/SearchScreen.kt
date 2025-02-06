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
import io.seoj17.soop.presentation.component.SoopMainLanguage
import io.seoj17.soop.presentation.component.SoopStar
import io.seoj17.soop.presentation.component.SoopUserContainer
import io.seoj17.soop.presentation.ui.search.component.SearchTextField
import io.seoj17.soop.presentation.utils.ImmutableList
import io.seoj17.soop.presentation.utils.noRippleSingleClick

@Composable
fun SearchScreen(onClickSearch: (String) -> Unit, repoList: ImmutableList<Any>) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchContainer(
            modifier = Modifier.fillMaxWidth(),
            onClickSearch = onClickSearch,
        )
        SearchResultContainer(modifier = Modifier.fillMaxSize(), repoList = repoList)
    }
}

@Composable
private fun SearchResultContainer(modifier: Modifier, repoList: ImmutableList<Any>) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(repoList) { index, item ->
            RepoInfoItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = if (index == 0) 20.dp else 13.dp,
                        bottom = if (index == repoList.lastIndex) 20.dp else 0.dp,
                    ),
                repoInfo = item,
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
private fun RepoInfoItem(modifier: Modifier, repoInfo: Any) {
    Column(modifier = modifier) {
        // TODO: RepoInfoItem 구현
        SoopUserContainer(
            modifier = Modifier.wrapContentSize(),
            userThumbnailUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            userName = "seoj17",
        )
        Text(modifier = Modifier.padding(top = 5.dp), text = "RepoName")
        Text(modifier = Modifier.padding(top = 10.dp), text = "RepoDescription")
        RepoPropertyContainer(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp),
            repoProperty = repoInfo,
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
private fun RepoPropertyContainer(modifier: Modifier, repoProperty: Any) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SoopStar(
            modifier = Modifier.wrapContentSize(),
            text = "14.5k",
        )
        SoopMainLanguage(
            modifier = Modifier.wrapContentSize(),
            text = "Kotlin",
        )
    }
}

@Composable
@Preview
private fun SearchScreenPreview() {
    SearchScreen(
        onClickSearch = {},
        repoList = ImmutableList(
            listOf(
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
                Any(),
            ),
        ),
    )
}
