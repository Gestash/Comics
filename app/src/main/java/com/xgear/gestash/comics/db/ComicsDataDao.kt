package com.xgear.gestash.comics.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.xgear.gestash.comics.net.Comics

@Dao
interface ComicsDataDao {
    @Insert
    fun insert(comics: Comics)

    @Transaction
    @Query("SELECT * FROM comics WHERE num = :num")
    fun getByNumber(num: Int?): Comics
}