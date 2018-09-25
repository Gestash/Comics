package com.example.gestash.comics

import com.google.gson.annotations.SerializedName

data class Comics(
        @SerializedName("month") val month: String,
        @SerializedName("num") val num: Int,
        @SerializedName("link") val link:String,
        @SerializedName("year") val year:String,
        @SerializedName("news")val news:String,
        @SerializedName("safe_title")val safe_title:String,
        @SerializedName("transcript")val transcript:String,
        @SerializedName("alt")val alt:String,
        @SerializedName("img")val img:String,
        @SerializedName("title")val title:String,
        @SerializedName("day")val day:String
)
