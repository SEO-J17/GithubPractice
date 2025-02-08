package io.seoj17.soop.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.seoj17.soop.data.BuildConfig
import io.seoj17.soop.data.di.qualifier.GitHubBaseUrl
import io.seoj17.soop.data.di.qualifier.RepoRetrofit
import io.seoj17.soop.data.di.qualifier.TimeOutPolicy
import io.seoj17.soop.data.interceptor.AuthInterceptor
import io.seoj17.soop.data.service.GithubRepoService
import io.seoj17.soop.data.service.UserService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @TimeOutPolicy
    fun provideConnectTimeoutPolicy(): Long {
        return 10_000L
    }

    @Singleton
    @Provides
    @GitHubBaseUrl
    fun provideGitHubBaseUrl(): String {
        return "https://api.github.com"
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }
    }

    @Singleton
    @Provides
    fun provideJson(): Json {
        return Json {
            // Json 큰따옴표 느슨하게 체크.
            isLenient = true
            // Field 값이 없는 경우 무시
            ignoreUnknownKeys = true
            // "null" 이 들어간경우 default Argument 값으로 대체
            coerceInputValues = true
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @TimeOutPolicy connectTimeoutPolicy: Long,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(
                connectTimeoutPolicy,
                TimeUnit.MILLISECONDS,
            )
            .build()
    }

    @Singleton
    @Provides
    @RepoRetrofit
    fun provideGithubRepoRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
        @GitHubBaseUrl baseUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType()),
        )
        .build()

    @Singleton
    @Provides
    fun provideLoginService(
        @RepoRetrofit retrofit: Retrofit,
    ): GithubRepoService = retrofit.create()

    @Singleton
    @Provides
    fun provideUserServices(
        @RepoRetrofit retrofit: Retrofit,
    ): UserService = retrofit.create()
}
