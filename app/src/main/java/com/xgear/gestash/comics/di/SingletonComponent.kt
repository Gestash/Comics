package com.xgear.gestash.comics.di

import com.xgear.gestash.comics.di.module.ContextModule
import com.xgear.gestash.comics.di.module.RoomModule
import com.xgear.gestash.comics.di.module.ServiceModule
import com.xgear.gestash.comics.domain.ComicsProvider
import com.xgear.gestash.comics.domain.ImageProvider
import com.xgear.gestash.comics.fs.GallerySaver
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ServiceModule::class,
                      ContextModule::class,
                      RoomModule::class])
@Singleton
interface SingletonComponent {
    fun comicsProvider(): ComicsProvider
    fun imageProvider(): ImageProvider
    fun gallerySaver(): GallerySaver
}