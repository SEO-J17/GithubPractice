package io.seoj17.soop.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.seoj17.soop.data.datasource.RepoDataSource
import io.seoj17.soop.data.datasource.RepoDataSourceImpl
import io.seoj17.soop.data.datasource.UserDataSource
import io.seoj17.soop.data.datasource.UserDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindLocalDataSource(repoDataSourceImpl: RepoDataSourceImpl): RepoDataSource

    @Binds
    @Singleton
    fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource
}
