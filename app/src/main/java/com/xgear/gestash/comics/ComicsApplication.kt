package com.xgear.gestash.comics

import android.app.Application
import com.xgear.gestash.comics.di.DaggerSingletonComponent
import com.xgear.gestash.comics.di.SingletonComponent

class ComicsApplication : Application() {
    lateinit var comp: SingletonComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        comp = DaggerSingletonComponent.builder().build()
    }

    companion object {
        private var instance: ComicsApplication? = null

        @JvmStatic
        fun getComponent() : SingletonComponent {
            return instance!!.comp
        }
    }
}