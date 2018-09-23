package com.example.gestash.comics

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("info.0.json ")
    fun getJson() : Call<Comics>
}