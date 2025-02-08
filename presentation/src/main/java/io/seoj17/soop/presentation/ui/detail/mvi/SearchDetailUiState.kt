package io.seoj17.soop.presentation.ui.detail.mvi

import io.seoj17.soop.presentation.base.UiState
import io.seoj17.soop.presentation.ui.detail.model.RepoDetail
import io.seoj17.soop.presentation.ui.detail.model.UserDetail

data class SearchDetailUiState(
    val isLoading: Boolean,
    val isBottomSheetVisible: Boolean,
    val repoDetail: RepoDetail,
    val userDetail: UserDetail,
) : UiState {
    companion object {
        fun initialize() = SearchDetailUiState(
            isLoading = false,
            isBottomSheetVisible = false,
            repoDetail = RepoDetail(
                userThumbnailUrl = "",
                repoName = "",
                mainLanguage = "",
                starCount = -1,
                watcherCount = -1,
                forkCount = -1,
                description = "",
            ),
            userDetail = UserDetail(
                userThumbnailUrl = "",
                userName = "",
                followerCount = -1,
                followingCount = -1,
                repoCount = -1,
                usedLanguage = "",
                bio = "",
            ),
        )
    }
}
