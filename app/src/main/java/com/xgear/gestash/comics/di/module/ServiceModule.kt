package com.xgear.gestash.comics.di.module

import android.content.Context
import com.xgear.gestash.comics.fs.ImageFileStorage
import com.xgear.gestash.comics.net.ComicsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {
    private val XKCD_BASE_URL = "http://xkcd.com/"

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
                .baseUrl(XKCD_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun comicsService(retrofit: Retrofit): ComicsService {
        return retrofit.create(ComicsService::class.java)
    }

    @Provides
    @Singleton
    fun imageFileStorage(context: Context): ImageFileStorage{
        return ImageFileStorage(cacheDir = context.cacheDir)
    }
}