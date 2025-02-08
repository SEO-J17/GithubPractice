package io.seoj17.soop.data.repository

import io.seoj17.soop.data.model.UserInfoDataModel

interface UserRepository {
    suspend fun getUserInfo(userName: String): UserInfoDataModel
}
