package com.xgear.gestash.comics.fs

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GallerySaver @Inject constructor(private val context: Context) {
    fun saveToGallery(cachedFile: File) {
//        val root = Environment.getExternalStorageDirectory().absolutePath+"/"
        val root = Environment.getExternalStorageDirectory()
        val galleryDir = File(root, "Comics")
        if (!galleryDir.exists()) {
            galleryDir.mkdir()
        }
        val file = File(galleryDir, cachedFile.name)
        file.createNewFile()
        file.writeBytes(cachedFile.readBytes())

        MediaScannerConnection.scanFile(context, arrayOf(file.path), null) { path, uri ->
            Log.i("ExternalStorage", "Scanned $path:");
            Log.i("ExternalStorage", "-> uri=$uri");
        }
    }
}