package io.seoj17.soop.data.datasource

import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel
import io.seoj17.soop.data.model.toDataModel
import io.seoj17.soop.data.service.GithubRepoService
import javax.inject.Inject

class RepoDataSourceImpl @Inject constructor(
    private val githubRepoService: GithubRepoService,
) : RepoDataSource {
    override suspend fun getRepoList(repoName: String): List<RepoInfoDataModel> {
        return githubRepoService.getRepoList(repoName).items.toDataModel()
    }

    override suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel {
        return githubRepoService.getUserRepoDetail(userName, repoName).toDataModel()
    }
}
