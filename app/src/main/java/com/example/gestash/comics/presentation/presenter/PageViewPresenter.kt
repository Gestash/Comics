package com.example.gestash.comics.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.gestash.comics.domain.ComicsProvider
import com.example.gestash.comics.presentation.view.PageView

@InjectViewState
class PageViewPresenter : MvpPresenter<PageView>() {

    private val comicsProvider = ComicsProvider()

    fun getComicsByNumber(number: Int) {
        comicsProvider.getComics(number) {
            if (it != null) {
                viewState.onComicsLoaded(it)
            } else {
                viewState.onComicsLoadFailure()
            }
        }
    }
}