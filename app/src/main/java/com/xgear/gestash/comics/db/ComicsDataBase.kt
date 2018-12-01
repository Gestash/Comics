package com.xgear.gestash.comics.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.xgear.gestash.comics.net.Comics

@Database(entities = [Comics::class], version = 1)
abstract class ComicsDataBase : RoomDatabase() {

    abstract fun comicsDataDao(): ComicsDataDao
}
