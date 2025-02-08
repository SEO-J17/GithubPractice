package io.seoj17.soop.data.model

import io.seoj17.soop.data.response.RepoDetailResponse

data class RepoDetailDataModel(
    val userThumbnailUrl: String,
    val repoName: String,
    val startCount: Int,
    val watcherCount: Int,
    val forkCount: Int,
    val description: String?,
)

fun RepoDetailResponse.toDataModel(): RepoDetailDataModel {
    return RepoDetailDataModel(
        userThumbnailUrl = owner.avatarUrl,
        repoName = name,
        startCount = stargazersCount,
        watcherCount = watchersCount,
        forkCount = forksCount,
        description = description,
    )
}
