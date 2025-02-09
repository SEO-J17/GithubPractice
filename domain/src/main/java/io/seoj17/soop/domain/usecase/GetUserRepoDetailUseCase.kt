package io.seoj17.soop.domain.usecase

import dagger.Reusable
import io.seoj17.soop.data.repository.RepoRepository
import io.seoj17.soop.domain.model.RepoDetailDomainModel
import io.seoj17.soop.domain.model.toDomainModel
import javax.inject.Inject

@Reusable
class GetUserRepoDetailUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
) {
    suspend operator fun invoke(userName: String, repoName: String): Result<RepoDetailDomainModel> {
        return runCatching { repoRepository.getRepoDetail(userName, repoName).toDomainModel() }
    }
}
