package com.xgear.gestash.comics.presentation.view

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun onComicsCountLoaded(count: Int)
    fun onComicsCountLoadFailure()
}