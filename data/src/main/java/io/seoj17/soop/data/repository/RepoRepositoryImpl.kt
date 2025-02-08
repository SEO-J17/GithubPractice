package io.seoj17.soop.data.repository

import io.seoj17.soop.data.datasource.RepoDataSource
import io.seoj17.soop.data.model.RepoInfoDataModel
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoDataSource: RepoDataSource,
) : RepoRepository {
    override suspend fun getRepoList(repoName: String): List<RepoInfoDataModel> {
        return repoDataSource.getRepoList(repoName)
    }
}
