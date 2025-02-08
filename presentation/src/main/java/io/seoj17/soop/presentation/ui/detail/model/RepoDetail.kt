package io.seoj17.soop.presentation.ui.detail.model

import io.seoj17.soop.domain.model.RepoDetailDomainModel

data class RepoDetail(
    val userThumbnailUrl: String,
    val repoName: String,
    val mainLanguage: String?,
    val starCount: Int,
    val watcherCount: Int,
    val forkCount: Int,
    val description: String?,
)

fun RepoDetailDomainModel.toUiModel(): RepoDetail {
    return RepoDetail(
        userThumbnailUrl = userThumbnailUrl,
        repoName = repoName,
        mainLanguage = mainLanguage,
        starCount = starCount,
        watcherCount = watcherCount,
        forkCount = forkCount,
        description = description,
    )
}
