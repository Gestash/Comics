package com.xgear.gestash.comics.domain

import android.net.Uri
import com.xgear.gestash.comics.fs.ImageFileStorage
import com.xgear.gestash.comics.net.ImageLoader
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageProvider @Inject constructor(private val imageLoader: ImageLoader,
                                        private val imageFileStorage: ImageFileStorage) {


    fun getImageFile(imageUrl: String, handler: (File?) -> Unit) {
        val fileName = Uri.parse(imageUrl).lastPathSegment
        val imageFile = imageFileStorage.getImageFile(fileName)

        if (imageFile.exists()) {
            handler(imageFile)
            return
        }

        imageLoader.loadImage(imageUrl) {
            val file = if (it != null) {
                imageFileStorage.saveImageFile(fileName, it)
            } else {
                null
            }
            handler(file)
        }
    }
}