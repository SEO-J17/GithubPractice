package io.seoj17.soop.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.seoj17.soop.data.repository.RepoRepository
import io.seoj17.soop.data.repository.RepoRepositoryImpl
import io.seoj17.soop.data.repository.UserRepository
import io.seoj17.soop.data.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepoRepository(repoRepositoryImpl: RepoRepositoryImpl): RepoRepository

    @Binds
    @Singleton
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
