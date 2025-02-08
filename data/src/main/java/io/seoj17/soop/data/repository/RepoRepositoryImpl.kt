package io.seoj17.soop.data.repository

import io.seoj17.soop.data.datasource.RepoDataSource
import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel
import io.seoj17.soop.data.model.UserRepoDataModel
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoDataSource: RepoDataSource,
) : RepoRepository {
    override suspend fun getRepoList(repoName: String): List<RepoInfoDataModel> {
        return repoDataSource.getRepoList(repoName)
    }

    override suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel {
        return repoDataSource.getRepoDetail(userName, repoName)
    }

    override suspend fun getUserRepoList(userName: String): List<UserRepoDataModel> {
        return repoDataSource.getUserRepoList(userName)
    }
}
