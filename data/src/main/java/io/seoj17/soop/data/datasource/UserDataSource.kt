package io.seoj17.soop.data.datasource

import io.seoj17.soop.data.model.UserInfoDataModel

interface UserDataSource {
    suspend fun getUserInfo(userName: String): UserInfoDataModel
}
