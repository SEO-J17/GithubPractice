package io.seoj17.soop.data.model

import io.seoj17.soop.data.response.RepoInfoResponse

data class RepoInfoDataModel(
    val userThumbnailUrl: String,
    val userName: String,
    val repoDescription: String?,
    val starCount: Int,
    val usedLanguage: String?,
)

fun RepoInfoResponse.toDataModel(): RepoInfoDataModel {
    return RepoInfoDataModel(
        userThumbnailUrl = owner.avatarUrl,
        userName = owner.login,
        repoDescription = description,
        starCount = stargazersCount,
        usedLanguage = language,
    )
}

fun List<RepoInfoResponse>.toDataModel(): List<RepoInfoDataModel> {
    return map { it.toDataModel() }
}
