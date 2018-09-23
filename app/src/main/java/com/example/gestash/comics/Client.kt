package com.example.gestash.comics

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
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

    fun getImage(url: (String?) -> Unit) {

        val call = service.getJson()
        call.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        url(data.img)
                    }
                } else {


                }
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun getPreviousImage(url: (String?) -> Unit){
        val call = service.getJson()
        call.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        url(data.img)
                    }
                } else {


                }
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}