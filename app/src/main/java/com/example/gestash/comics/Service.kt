package com.example.gestash.comics

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("info.0.json ")
    fun getComics() : Call<Comics>

    @GET("{number}/info.0.json ")
    fun getComicsByNumber(@Path("number") number:Int): Call<Comics>
}