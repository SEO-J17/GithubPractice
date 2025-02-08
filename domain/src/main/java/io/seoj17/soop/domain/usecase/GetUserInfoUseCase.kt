package io.seoj17.soop.domain.usecase

import dagger.Reusable
import io.seoj17.soop.data.repository.UserRepository
import io.seoj17.soop.domain.model.UserInfoDomainModel
import io.seoj17.soop.domain.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(userName: String): Flow<UserInfoDomainModel> {
        return flow { emit(userRepository.getUserInfo(userName).toDomainModel()) }
    }
}
