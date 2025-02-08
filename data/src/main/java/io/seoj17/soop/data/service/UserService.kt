package io.seoj17.soop.data.service

import io.seoj17.soop.data.response.UserInfoResponse
import io.seoj17.soop.data.response.UserRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users/{username}")
    suspend fun getUserInfo(
        @Path("username") userName: String,
    ): UserInfoResponse

    @GET("users/{username}/repos")
    suspend fun getUserRepoList(
        @Path("username") userName: String,
    ): List<UserRepoResponse>
}
