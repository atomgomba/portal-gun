package com.ekezet.portalgun.base.di

import android.app.Application
import com.ekezet.portalgun.base.BuildConfig
import com.ekezet.portalgun.base.api.adapters.DateTimeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class,
        DataModule::class,
        RepoModule::class,
    ]
)
object BaseModule {
    @Provides @Singleton fun provideOkHttpClient(app: Application) =
        OkHttpClient.Builder()
            .cache(
                Cache(
                    directory = File(app.cacheDir, "http_cache"),
                    maxSize = 50L * 1024L * 1024L // 50 MiB
                )
            )
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = BODY
                }
            )
            .build()

    @Provides @Singleton fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(DateTimeAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides @Singleton fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}
