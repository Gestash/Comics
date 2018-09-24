package com.example.gestash.comics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    val comicsProvider = ComicsProvider()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView.setOnTouchListener(object :OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                comicsProvider.getPreviousComics(::loadImage)

            }

            override fun onSwipeRight() {
                comicsProvider.getNextComics(::loadImage)

            }
        }

        )

        comicsProvider.getLastComics(::loadImage)

        btn_prev.onClick {
            comicsProvider.getPreviousComics(::loadImage)
        }

       btn_next.onClick {
           comicsProvider.getNextComics(::loadImage)
       }
    }

    private fun loadImage(url:String) {
        Picasso.get().load(url).into(imageView)
    }
}
