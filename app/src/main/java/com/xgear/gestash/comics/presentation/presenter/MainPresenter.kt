package com.xgear.gestash.comics.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.xgear.gestash.comics.ComicsApplication
import com.xgear.gestash.comics.presentation.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private val comicsProvider = ComicsApplication.getComponent().comicsProvider()

    fun loadComicsCount() {
        comicsProvider.getComicsCount {
            if (it != null) {
                viewState.onComicsCountLoaded(it)
            } else {
                viewState.onComicsCountLoadFailure()
            }
        }
    }
}