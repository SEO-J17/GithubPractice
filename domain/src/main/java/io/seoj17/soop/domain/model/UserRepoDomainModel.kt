package io.seoj17.soop.domain.model

import io.seoj17.soop.data.model.UserRepoDataModel

data class UserRepoDomainModel(
    val usedLanguage: String?,
)

fun UserRepoDataModel.toDomainModel(): UserRepoDomainModel {
    return UserRepoDomainModel(
        usedLanguage = usedLanguage,
    )
}

fun List<UserRepoDataModel>.toDomainModel(): List<UserRepoDomainModel> {
    return map { it.toDomainModel() }
}
