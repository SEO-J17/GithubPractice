package io.seoj17.soop.data.repository

import io.seoj17.soop.data.datasource.UserDataSource
import io.seoj17.soop.data.model.UserInfoDataModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override suspend fun getUserInfo(userName: String): UserInfoDataModel {
        return userDataSource.getUserInfo(userName)
    }
}
