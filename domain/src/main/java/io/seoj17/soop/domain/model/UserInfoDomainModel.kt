package io.seoj17.soop.domain.model

import io.seoj17.soop.data.model.UserInfoDataModel

data class UserInfoDomainModel(
    val userThumbnailUrl: String,
    val userName: String,
    val followerCount: Int,
    val followingCount: Int,
    val bio: String?,
)

fun UserInfoDataModel.toDomainModel(): UserInfoDomainModel {
    return UserInfoDomainModel(
        userThumbnailUrl = userThumbnailUrl,
        userName = userName,
        followerCount = followerCount,
        followingCount = followingCount,
        bio = bio,
    )
}
