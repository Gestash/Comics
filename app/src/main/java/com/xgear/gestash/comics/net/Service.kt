package com.xgear.gestash.comics.net

import retrofit2.Call
import retrofit2.http.*

interface Service {
    @GET("info.0.json ")
    fun getLastComics() : Call<Comics>

    @GET("{number}/info.0.json ")
    fun getComicsByNumber(@Path("number") number:Int): Call<Comics>
}