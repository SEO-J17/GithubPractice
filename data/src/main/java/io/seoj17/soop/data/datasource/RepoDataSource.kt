package io.seoj17.soop.data.datasource

import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel
import io.seoj17.soop.data.model.UserRepoDataModel

interface RepoDataSource {
    suspend fun getRepoList(repoName: String): List<RepoInfoDataModel>
    suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel
    suspend fun getUserRepoList(userName: String): List<UserRepoDataModel>
}
