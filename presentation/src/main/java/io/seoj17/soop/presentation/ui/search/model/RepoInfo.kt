package io.seoj17.soop.presentation.ui.search.model

import io.seoj17.soop.domain.model.RepoInfoDomainModel

data class RepoInfo(
    val userThumbnailUrl: String,
    val userName: String,
    val repoName: String,
    val repoDescription: String?,
    val starCount: Int,
    val usedLanguage: String?,
)

fun RepoInfoDomainModel.toUiModel(): RepoInfo {
    return RepoInfo(
        userThumbnailUrl = userThumbnailUrl,
        userName = userName,
        repoName = repoName,
        repoDescription = repoDescription,
        starCount = starCount,
        usedLanguage = usedLanguage,
    )
}

fun List<RepoInfoDomainModel>.toUiModel(): List<RepoInfo> {
    return map { it.toUiModel() }
}
