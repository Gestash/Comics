package com.xgear.gestash.comics.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestAPI {
    private val XKCD_BASE_URL = "http://xkcd.com/"

    private val service: Service

    private var call: Call<Comics>? = null

    init {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()


        val retrofit = Retrofit.Builder()
                .baseUrl(XKCD_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        service = retrofit.create(Service::class.java)
    }

    fun getLast(handler: (Comics?) -> Unit) {

        call?.cancel()

        call = service.getLastComics()

        call?.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                handler(response.body())
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                handler(null)
            }
        })
    }

    fun getComicsByNumber(number: Int, handler: (Comics?) -> Unit) {

        call?.cancel()

        call = service.getComicsByNumber(number)

        call?.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                handler(response.body())
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                handler(null)
            }
        })
    }

}