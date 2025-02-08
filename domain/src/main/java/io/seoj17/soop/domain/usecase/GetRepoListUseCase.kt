package io.seoj17.soop.domain.usecase

import dagger.Reusable
import io.seoj17.soop.data.repository.RepoRepository
import io.seoj17.soop.domain.model.RepoInfoDomainModel
import io.seoj17.soop.domain.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetRepoListUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
) {
    operator fun invoke(repoName: String): Flow<List<RepoInfoDomainModel>> {
        return flow {
            emit(repoRepository.getRepoList(repoName).toDomainModel())
        }
    }
}
