package io.seoj17.soop.data.repository

import androidx.paging.Pager
import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel
import io.seoj17.soop.data.model.UserRepoDataModel

interface RepoRepository {
    fun getRepoList(repoName: String): Pager<Int, RepoInfoDataModel>
    suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel
    suspend fun getUserRepoList(userName: String): List<UserRepoDataModel>
}
