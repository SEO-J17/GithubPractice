package io.seoj17.soop.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import dagger.Reusable
import io.seoj17.soop.data.repository.RepoRepository
import io.seoj17.soop.domain.model.RepoInfoDomainModel
import io.seoj17.soop.domain.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetRepoListUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
) {
    operator fun invoke(repoName: String): Flow<PagingData<RepoInfoDomainModel>> {
        return repoRepository
            .getRepoList(repoName = repoName)
            .flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }
    }
}
