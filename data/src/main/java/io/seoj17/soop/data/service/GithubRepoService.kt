package io.seoj17.soop.data.service

import io.seoj17.soop.data.response.GithubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepoService {
    @GET("search/repositories")
    suspend fun getRepoList(
        @Query(value = "q") repoName: String,
    ): GithubRepoResponse
}
