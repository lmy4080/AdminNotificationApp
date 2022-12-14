package com.leetotheyutothelee.adminnotificationapp.di

import android.content.Context
import android.content.pm.ApplicationInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leetotheyutothelee.adminnotificationapp.BuildConfig
import com.leetotheyutothelee.adminnotificationapp.constant.NetworkConstant
import com.leetotheyutothelee.adminnotificationapp.data.remote.ApiService
import com.leetotheyutothelee.adminnotificationapp.data.repository.ApiRepository
import com.leetotheyutothelee.adminnotificationapp.data.repository.ApiRepositoryImpl
import com.leetotheyutothelee.adminnotificationapp.data.source.ApiDataSource
import com.leetotheyutothelee.adminnotificationapp.data.source.ApiDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(NetworkConstant.SERVER_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        with(OkHttpClient().newBuilder()) {
            cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .method(it.request().method(), it.request().body())
                        .build()
                )
            }
            addInterceptor(httpLoggingInterceptor)
            return build()
        }
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiDataSource(apiService: ApiService): ApiDataSource {
        return ApiDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideApiRepository(apiDataSource: ApiDataSource): ApiRepository {
        return ApiRepositoryImpl(apiDataSource)
    }
}