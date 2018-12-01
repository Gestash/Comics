package com.xgear.gestash.comics.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.xgear.gestash.comics.db.ComicsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun comicsDataBase(context: Context): ComicsDataBase {
        return Room.databaseBuilder(context,
                ComicsDataBase::class.java,
                "comics.db")
                .build()
    }

    @Provides
    @Singleton
    fun providesComicsDao(database: ComicsDataBase) = database.comicsDataDao()

}