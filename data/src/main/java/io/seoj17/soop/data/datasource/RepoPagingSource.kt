package io.seoj17.soop.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.seoj17.soop.data.model.RepoInfoDataModel

class RepoPagingSource(
    private val repoDataSource: RepoDataSource,
    private val repoName: String,
) : PagingSource<Int, RepoInfoDataModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoInfoDataModel> {
        val page = params.key ?: START_PAGE

        return try {
            val repoInfoList = repoDataSource.getRepoList(
                repoName = repoName,
                page = page,
                perPage = params.loadSize,
            )

            if (repoInfoList.isEmpty()) {
                throw NoSuchElementException()
            }

            LoadResult.Page(
                data = repoInfoList,
                prevKey = if (page == START_PAGE) null else page - 1,
                nextKey = if (repoInfoList.size < params.loadSize) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoInfoDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val START_PAGE = 1
    }
}
