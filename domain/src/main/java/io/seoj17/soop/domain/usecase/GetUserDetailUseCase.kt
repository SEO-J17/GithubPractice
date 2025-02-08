package io.seoj17.soop.domain.usecase

import dagger.Reusable
import io.seoj17.soop.domain.model.UserDetailDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@Reusable
class GetUserDetailUseCase @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserRepoListUseCase: GetUserRepoListUseCase,
) {
    operator fun invoke(userName: String): Flow<UserDetailDomainModel> {
        val userInfoFlow = getUserInfoUseCase(userName)
        val userRepoListFlow = getUserRepoListUseCase(userName)
        return combine(userInfoFlow, userRepoListFlow) { userInfo, userRepoList ->
            UserDetailDomainModel.toModel(userInfo, userRepoList)
        }
    }
}
