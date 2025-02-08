package io.seoj17.soop.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.seoj17.soop.data.datasource.RepoDataSource
import io.seoj17.soop.data.datasource.RepoDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindLocalDataSource(repoDataSourceImpl: RepoDataSourceImpl): RepoDataSource
}
