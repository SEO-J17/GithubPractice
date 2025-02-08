package io.seoj17.soop.presentation.ui.detail.model

import io.seoj17.soop.domain.model.UserDetailDomainModel

data class UserDetail(
    val userThumbnailUrl: String,
    val userName: String,
    val followerCount: Int,
    val followingCount: Int,
    val repoCount: Int,
    val usedLanguage: String,
    val bio: String?,
)

fun UserDetailDomainModel.toUiModel(): UserDetail {
    return UserDetail(
        userThumbnailUrl = userThumbnailUrl,
        userName = userName,
        followerCount = followerCount,
        followingCount = followingCount,
        repoCount = repoCount,
        usedLanguage = usedLanguageList
            .distinct()
            .joinToString(
                separator = ", ",
                transform = { it },
            ),
        bio = bio,
    )
}
