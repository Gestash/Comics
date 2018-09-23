package com.example.gestash.comics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    val client = Client()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        client.getImage { url -> Picasso.get().load(url).into(imageView)}

        btn_prev.onClick {
            //val url =
            client.getImage { url -> Picasso.get().load(url).into(imageView)}
        }

        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView)
    }
}
