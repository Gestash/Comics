package com.xgear.gestash.comics.di

import com.xgear.gestash.comics.di.module.ContextModule
import com.xgear.gestash.comics.di.module.ServiceModule
import com.xgear.gestash.comics.domain.ComicsProvider
import com.xgear.gestash.comics.domain.ImageProvider
import com.xgear.gestash.comics.fs.ImageFileStorage
import com.xgear.gestash.comics.net.ComicsService
import com.xgear.gestash.comics.net.ImageLoader
import com.xgear.gestash.comics.net.RestAPI
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ServiceModule::class,
                      ContextModule::class])
@Singleton
interface SingletonComponent {
    fun comicsService(): ComicsService
    fun restApi(): RestAPI
    fun comicsProvider(): ComicsProvider
    fun imageProvider(): ImageProvider
    fun imageLoader(): ImageLoader
    fun imageFileStorage(): ImageFileStorage
}