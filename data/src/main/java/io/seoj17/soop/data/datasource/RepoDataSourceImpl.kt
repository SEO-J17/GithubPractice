package io.seoj17.soop.data.datasource

import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel
import io.seoj17.soop.data.model.UserRepoDataModel
import io.seoj17.soop.data.model.toDataModel
import io.seoj17.soop.data.service.GithubRepoService
import io.seoj17.soop.data.service.UserService
import javax.inject.Inject

class RepoDataSourceImpl @Inject constructor(
    private val githubRepoService: GithubRepoService,
    private val userService: UserService,
) : RepoDataSource {
    override suspend fun getRepoList(
        repoName: String,
        page: Int,
        perPage: Int,
    ): List<RepoInfoDataModel> {
        return githubRepoService.getRepoList(repoName, page, perPage).items.toDataModel()
    }

    override suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel {
        return githubRepoService.getUserRepoDetail(userName, repoName).toDataModel()
    }

    override suspend fun getUserRepoList(userName: String): List<UserRepoDataModel> {
        return userService.getUserRepoList(userName).toDataModel()
    }
}
