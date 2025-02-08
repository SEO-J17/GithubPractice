package io.seoj17.soop.domain.model

import io.seoj17.soop.data.model.RepoDetailDataModel

data class RepoDetailDomainModel(
    val userThumbnailUrl: String,
    val repoName: String,
    val mainLanguage: String?,
    val starCount: Int,
    val watcherCount: Int,
    val forkCount: Int,
    val description: String?,
)

fun RepoDetailDataModel.toDomainModel(): RepoDetailDomainModel {
    return RepoDetailDomainModel(
        userThumbnailUrl = userThumbnailUrl,
        repoName = repoName,
        mainLanguage = mainLanguage,
        starCount = starCount,
        watcherCount = watcherCount,
        forkCount = forkCount,
        description = description,
    )
}
