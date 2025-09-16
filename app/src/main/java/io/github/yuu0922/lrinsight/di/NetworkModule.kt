package io.github.yuu0922.lrinsight.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.yuu0922.lrinsight.BuildConfig
import io.github.yuu0922.lrinsight.api.LericoApi
import io.github.yuu0922.lrinsight.api.interceptor.UserAgentInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideUserAgentInterceptor(
        @ApplicationContext appContext: Context
    ): UserAgentInterceptor {
        return UserAgentInterceptor(appContext)
    }

    @Provides
    fun provideOkHttpClient(
        userAgentInterceptor: UserAgentInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(userAgentInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    fun provideLericoApi(
        okHttpClient: OkHttpClient,
        json: Json
    ): LericoApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://rangers.lerico.net")
            .build()
            .create()
    }
}