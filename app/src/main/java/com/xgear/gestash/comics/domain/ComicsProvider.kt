package com.xgear.gestash.comics.domain

import com.xgear.gestash.comics.db.ComicsDataDao
import com.xgear.gestash.comics.net.Comics
import com.xgear.gestash.comics.net.RestAPI
import com.xgear.gestash.comics.presentation.model.ComicsViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicsProvider @Inject constructor(private val restAPI: RestAPI, private val providesComicsDao: ComicsDataDao) {

    private var lastComicsNumber = 0

    fun getComicsCount(handler: (Int?) -> Unit) {

        restAPI.getLast { comics ->
            val comicsNum = if (comics != null) {
                lastComicsNumber = comics.num
                comics.num
            } else {
                null
            }
            handler(comicsNum)
        }
    }

    fun getComics(number: Int, handler: (ComicsViewModel?) -> Unit) {

        getComicsFromDB(number) { comics ->
            if (comics != null) {
                handler(ComicsViewModel(comics))
                return@getComicsFromDB
            }

            restAPI.getComicsByNumber(number) {
                val cvm = if (it != null) {
                    saveComicsToDB(it)
                    ComicsViewModel(it)
                } else {
                    null
                }
                handler(cvm)
            }
        }
    }

    private fun getComicsFromDB(number: Int, handler: (Comics?) -> Unit) {

        doAsync {
            val comics = providesComicsDao.getByNumber(number)

            uiThread {
                handler(comics)
            }
        }
    }

    private fun saveComicsToDB(comics: Comics) {
        doAsync {
            providesComicsDao.insert(comics)
        }
    }
}