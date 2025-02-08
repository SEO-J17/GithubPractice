package io.seoj17.soop.data.model

import io.seoj17.soop.data.response.UserRepoResponse

data class UserRepoDataModel(
    val usedLanguage: String?,
)

fun UserRepoResponse.toDataModel(): UserRepoDataModel {
    return UserRepoDataModel(
        usedLanguage = language,
    )
}

fun List<UserRepoResponse>.toDataModel(): List<UserRepoDataModel> {
    return map { it.toDataModel() }
}
