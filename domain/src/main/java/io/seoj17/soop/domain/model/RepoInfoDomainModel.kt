package io.seoj17.soop.domain.model

import io.seoj17.soop.data.model.RepoInfoDataModel

data class RepoInfoDomainModel(
    val userThumbnailUrl: String,
    val userName: String,
    val repoName: String,
    val repoDescription: String?,
    val starCount: Int,
    val usedLanguage: String?,
)

fun RepoInfoDataModel.toDomainModel(): RepoInfoDomainModel {
    return RepoInfoDomainModel(
        userThumbnailUrl = userThumbnailUrl,
        userName = userName,
        repoName = repoName,
        repoDescription = repoDescription,
        starCount = starCount,
        usedLanguage = usedLanguage,
    )
}

fun List<RepoInfoDataModel>.toDomainModel(): List<RepoInfoDomainModel> {
    return map { it.toDomainModel() }
}
