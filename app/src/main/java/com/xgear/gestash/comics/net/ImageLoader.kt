package com.xgear.gestash.comics.net

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import okhttp3.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoader @Inject constructor(private val okHttpClient: OkHttpClient) {

    fun loadImage(imageUrl: String, handler: (ByteArray?) -> Unit) {

        val request = Request.Builder().url(imageUrl).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val bytes = if (!response.isSuccessful) null else response.body()?.bytes()

                launch(UI) {
                    handler(bytes)
                }
            }

            override fun onFailure(call: Call, e: IOException) {

                launch(UI) {
                    handler(null)
                }
            }

        })

    }
}