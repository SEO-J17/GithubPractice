package io.seoj17.soop.data.service

import io.seoj17.soop.data.response.GithubRepoResponse
import io.seoj17.soop.data.response.RepoDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubRepoService {
    @GET("search/repositories")
    suspend fun getRepoList(
        @Query(value = "q") repoName: String,
    ): GithubRepoResponse

    @GET("repos/{owner}/{repo}")
    suspend fun getUserRepoDetail(
        @Path(value = "owner") userName: String,
        @Path(value = "repo") repoName: String,
    ): RepoDetailResponse
}
