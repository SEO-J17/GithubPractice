package io.seoj17.soop.data.repository

import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel

interface RepoRepository {
    suspend fun getRepoList(repoName: String): List<RepoInfoDataModel>
    suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel
}
