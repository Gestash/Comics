package com.example.gestash.comics

import java.util.*

class ComicsProvider {
    private val restAPI = RestAPI()
    private var lastComicsNumber = 0
    private var currentComicsNumber = 0

    fun getLastComics(result: (ComicsViewModel) -> Unit) {
        restAPI.getComics(success = { comics ->
            currentComicsNumber = comics.num
            lastComicsNumber = comics.num
            val number = "#$currentComicsNumber"
            val date = comics.day + "." + comics.month + "." + comics.year
            result(ComicsViewModel(date, number, comics.img, comics.title))
        }, failure = { error ->
            TODO()
        })
    }

    fun getPreviousComics(result: (ComicsViewModel) -> Unit) {

        if (currentComicsNumber == 1) {
            return
        }

        restAPI.getComicsByNumber(currentComicsNumber - 1, success = { comics ->
            currentComicsNumber = comics.num
            val number = "#$currentComicsNumber"
            val date = comics.day + "." + comics.month + "." + comics.year
            result(ComicsViewModel(date, number, comics.img, comics.title))
        }, failure = { error ->
            TODO()
        })
    }

    fun getNextComics(result: (ComicsViewModel) -> Unit) {
        if (currentComicsNumber == lastComicsNumber) {
            return
        }
        restAPI.getComicsByNumber(currentComicsNumber + 1, success = { comics ->
            currentComicsNumber = comics.num
            val number = "#$currentComicsNumber"
            val date = comics.day + "." + comics.month + "." + comics.year
            result(ComicsViewModel(date, number, comics.img, comics.title))
        }, failure = { error ->
            TODO()
        })
    }

    fun getRandomComics(result: (ComicsViewModel) -> Unit) {
        val random = Random()
        val randomComics = random.nextInt(lastComicsNumber)
        restAPI.getComicsByNumber(randomComics, success = { comics ->
            currentComicsNumber = comics.num
            val number = "#$currentComicsNumber"
            val date = comics.day + "." + comics.month + "." + comics.year
            result(ComicsViewModel(date, number, comics.img, comics.title))
        }, failure = { error ->
            TODO()
        })
    }

}