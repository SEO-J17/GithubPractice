package io.seoj17.soop.data.datasource

import io.seoj17.soop.data.model.UserInfoDataModel
import io.seoj17.soop.data.model.toDataModel
import io.seoj17.soop.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserDataSource {
    override suspend fun getUserInfo(userName: String): UserInfoDataModel {
        return userService.getUserInfo(userName).toDataModel()
    }
}
