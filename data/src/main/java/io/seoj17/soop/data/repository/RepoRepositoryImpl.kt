package io.seoj17.soop.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.seoj17.soop.data.datasource.RepoDataSource
import io.seoj17.soop.data.datasource.RepoPagingSource
import io.seoj17.soop.data.model.RepoDetailDataModel
import io.seoj17.soop.data.model.RepoInfoDataModel
import io.seoj17.soop.data.model.UserRepoDataModel
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoDataSource: RepoDataSource,
) : RepoRepository {
    override fun getRepoList(repoName: String): Pager<Int, RepoInfoDataModel> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = NETWORK_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                RepoPagingSource(repoDataSource = repoDataSource, repoName = repoName)
            },
        )
    }

    override suspend fun getRepoDetail(userName: String, repoName: String): RepoDetailDataModel {
        return repoDataSource.getRepoDetail(userName, repoName)
    }

    override suspend fun getUserRepoList(userName: String): List<UserRepoDataModel> {
        return repoDataSource.getUserRepoList(userName)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }
}
