package com.xgear.gestash.comics.fs

import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageFileStorage @Inject constructor(private val cacheDir: File) {

    fun getImageFile(fileName: String): File {
        return File(cacheDir, fileName)
    }

    fun saveImageFile(fileName: String, data: ByteArray): File {
        val file = File(cacheDir, fileName)
        file.createNewFile()
        file.writeBytes(data)
        return file
    }
}