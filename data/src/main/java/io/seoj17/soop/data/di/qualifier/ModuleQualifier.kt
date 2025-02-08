package io.seoj17.soop.data.di.qualifier

import jakarta.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GitHubBaseUrl

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TimeOutPolicy

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RepoRetrofit
