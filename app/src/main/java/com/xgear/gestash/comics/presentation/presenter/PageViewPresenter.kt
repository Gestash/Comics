package com.xgear.gestash.comics.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.xgear.gestash.comics.ComicsApplication
import com.xgear.gestash.comics.presentation.model.ComicsViewModel
import com.xgear.gestash.comics.presentation.view.PageView


@InjectViewState
    class PageViewPresenter : MvpPresenter<PageView>() {

    private val comicsProvider = ComicsApplication.getComponent().comicsProvider()

    var comics: ComicsViewModel? = null

    fun getComicsByNumber(number: Int) {
        comicsProvider.getComics(number) {
            if (it != null) {
                viewState.onComicsLoaded(it)
                comics = it
            } else {
                viewState.onComicsLoadFailure()
            }
        }
    }
}