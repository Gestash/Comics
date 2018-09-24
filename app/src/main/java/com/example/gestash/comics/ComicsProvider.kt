package com.example.gestash.comics

class ComicsProvider {
    private val restAPI = RestAPI()
    private var lastComicsNumber = 0
    private var currentComicsNumber = 0

    fun getLastComics(result: (String) -> Unit) {
        restAPI.getComics(success = { comics ->
            currentComicsNumber = comics.num
            lastComicsNumber = comics.num
            result(comics.img)
        }, failure = { error ->
            TODO()
        })
    }

    fun getPreviousComics(result: (String) -> Unit) {

        if (currentComicsNumber == 1) {
            return
        }

        restAPI.getComicsByNumber(currentComicsNumber - 1, success = { comics ->
            currentComicsNumber = comics.num
            result(comics.img)
        }, failure = { error ->
            TODO()
        })
    }

    fun getNextComics(result: (String) -> Unit) {
        if (currentComicsNumber == lastComicsNumber) {
            return
        }
        restAPI.getComicsByNumber(currentComicsNumber + 1, success = { comics ->
            currentComicsNumber = comics.num
            result(comics.img)
        }, failure = { error ->
            TODO()
        })
    }
}