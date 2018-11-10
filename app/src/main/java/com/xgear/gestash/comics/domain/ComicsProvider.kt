package com.xgear.gestash.comics.domain

import com.xgear.gestash.comics.net.RestAPI
import com.xgear.gestash.comics.presentation.model.ComicsViewModel

class ComicsProvider {
    private val restAPI = RestAPI()
    private var lastComicsNumber = 0

    fun getComicsCount(handler: (Int?) -> Unit) {

        restAPI.getLast { comics ->
            if (comics == null) {
                handler(null)
                return@getLast
            }

            lastComicsNumber = comics.num
            handler(comics.num)
        }
    }

    fun getComics(number: Int, handler: (ComicsViewModel?) -> Unit) {

        restAPI.getComicsByNumber(number) { comics ->
            if (comics == null) {
                handler(null)
                return@getComicsByNumber
            }

            handler(ComicsViewModel(comics))
        }
    }

}