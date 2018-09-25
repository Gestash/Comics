package com.example.gestash.comics.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Error

class RestAPI {
    private val service: Service

    init {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()


        val retrofit = Retrofit.Builder()
                .baseUrl("http://xkcd.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        service = retrofit.create(Service::class.java)
    }

    fun getComics(success: (Comics) -> Unit, failure: (Error) -> Unit) {

        val call = service.getComics()
        call.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                if (response.isSuccessful) {
                    val comics = response.body()
                    if (comics == null) {
                        failure(Error("error"))
                        return
                    }
                    success(comics)
                } else {
                    failure(Error("error"))
                }
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                failure(Error("error"))
            }
        })
    }

    fun getComicsByNumber(number:Int, success: (Comics) -> Unit, failure: (Error) -> Unit) {

        val call = service.getComicsByNumber(number)
        call.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                if (response.isSuccessful) {
                    val comics = response.body()
                    if (comics == null) {
                        failure(Error("error"))
                        return
                    }
                    success(comics)
                } else {
                    failure(Error("error"))
                }
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                failure(Error("error"))
            }
        })
    }

}