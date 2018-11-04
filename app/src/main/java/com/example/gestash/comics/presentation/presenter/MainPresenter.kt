package com.example.gestash.comics.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.gestash.comics.domain.ComicsProvider
import com.example.gestash.comics.presentation.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private val comicsProvider = ComicsProvider()

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