package io.seoj17.soop.domain.model

data class UserDetailDomainModel(
    val userThumbnailUrl: String,
    val userName: String,
    val followerCount: Int,
    val followingCount: Int,
    val repoCount: Int,
    val usedLanguageList: List<String>,
    val bio: String?,
) {
    companion object {
        fun toModel(
            userInfo: UserInfoDomainModel,
            userRepoList: List<UserRepoDomainModel>,
        ): UserDetailDomainModel {
            return UserDetailDomainModel(
                userThumbnailUrl = userInfo.userThumbnailUrl,
                userName = userInfo.userName,
                followerCount = userInfo.followerCount,
                followingCount = userInfo.followingCount,
                repoCount = userRepoList.size,
                usedLanguageList = userRepoList.mapNotNull { it.usedLanguage },
                bio = userInfo.bio,
            )
        }
    }
}
