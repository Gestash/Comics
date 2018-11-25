package com.xgear.gestash.comics

import android.app.Application
import com.xgear.gestash.comics.di.DaggerSingletonComponent
import com.xgear.gestash.comics.di.SingletonComponent
import com.xgear.gestash.comics.di.module.ContextModule

class ComicsApplication : Application() {
    lateinit var comp: SingletonComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        comp = DaggerSingletonComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
    }

    companion object {
        private var instance: ComicsApplication? = null

        @JvmStatic
        fun getComponent() : SingletonComponent {
            return instance!!.comp
        }
    }
}