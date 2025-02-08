package io.seoj17.soop.data.model

import io.seoj17.soop.data.response.UserInfoResponse

data class UserInfoDataModel(
    val userThumbnailUrl: String,
    val userName: String,
    val followerCount: Int,
    val followingCount: Int,
    val bio: String?,
)

fun UserInfoResponse.toDataModel(): UserInfoDataModel {
    return UserInfoDataModel(
        userThumbnailUrl = avatarUrl,
        userName = login,
        followerCount = followers,
        followingCount = following,
        bio = bio,
    )
}
