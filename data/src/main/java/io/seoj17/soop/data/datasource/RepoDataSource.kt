package io.seoj17.soop.data.datasource

import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel

interface RepoDataSource {
    suspend fun getRepoList(repoName: String): List<RepoInfoDataModel>
    suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel
}
