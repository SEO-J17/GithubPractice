package io.seoj17.soop.data.model

import io.seoj17.soop.data.response.RepoDetailResponse

data class RepoDetailDataModel(
    val userThumbnailUrl: String,
    val repoName: String,
    val starCount: Int,
    val mainLanguage: String?,
    val watcherCount: Int,
    val forkCount: Int,
    val description: String?,
)

fun RepoDetailResponse.toDataModel(): RepoDetailDataModel {
    return RepoDetailDataModel(
        userThumbnailUrl = owner.avatarUrl,
        repoName = name,
        mainLanguage = language,
        starCount = stargazersCount,
        watcherCount = watchersCount,
        forkCount = forksCount,
        description = description,
    )
}
