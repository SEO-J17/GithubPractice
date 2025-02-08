package io.seoj17.soop.domain.usecase

import dagger.Reusable
import io.seoj17.soop.data.repository.RepoRepository
import io.seoj17.soop.domain.model.UserRepoDomainModel
import io.seoj17.soop.domain.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetUserRepoListUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
) {
    operator fun invoke(userName: String): Flow<List<UserRepoDomainModel>> {
        return flow { emit(repoRepository.getUserRepoList(userName).toDomainModel()) }
    }
}
