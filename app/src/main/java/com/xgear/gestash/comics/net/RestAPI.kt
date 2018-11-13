package com.xgear.gestash.comics.net

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestAPI @Inject constructor(private val service: ComicsService) {

    fun getLast(handler: (Comics?) -> Unit) {

        val call = service.getLastComics()

        call.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                handler(response.body())
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                handler(null)
            }
        })
    }

    fun getComicsByNumber(number: Int, handler: (Comics?) -> Unit) {

        val call = service.getComicsByNumber(number)

        call.enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                handler(response.body())
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {
                handler(null)
            }
        })
    }

}