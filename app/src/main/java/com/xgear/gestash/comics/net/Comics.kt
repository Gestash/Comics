package com.xgear.gestash.comics.net

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Comics(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("num")
        val num: Int,

        @ColumnInfo
        @SerializedName("month")
        val month: Int,

        @ColumnInfo
        @SerializedName("link")
        val link: String,

        @ColumnInfo
        @SerializedName("year")
        val year: Int,

        @ColumnInfo
        @SerializedName("news")
        val news: String,

        @ColumnInfo
        @SerializedName("safe_title")
        val safe_title: String,

        @ColumnInfo
        @SerializedName("transcript")
        val transcript: String,

        @ColumnInfo
        @SerializedName("alt")
        val alt: String,

        @ColumnInfo
        @SerializedName("img")
        val img: String,

        @ColumnInfo
        @SerializedName("title")
        val title: String,

        @ColumnInfo
        @SerializedName("day")
        val day: Int
)
