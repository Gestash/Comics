package com.xgear.gestash.comics.di

import com.xgear.gestash.comics.di.module.ContextModule
import com.xgear.gestash.comics.di.module.ServiceModule
import com.xgear.gestash.comics.domain.ComicsProvider
import com.xgear.gestash.comics.domain.ImageProvider
import com.xgear.gestash.comics.fs.GallerySaver
import com.xgear.gestash.comics.fs.ImageFileStorage
import com.xgear.gestash.comics.net.ComicsService
import com.xgear.gestash.comics.net.ImageLoader
import com.xgear.gestash.comics.net.RestAPI
import com.xgear.gestash.comics.db.ComicsDataDao
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
    fun gallerySaver(): GallerySaver
    fun providesComicsDao(): ComicsDataDao
}