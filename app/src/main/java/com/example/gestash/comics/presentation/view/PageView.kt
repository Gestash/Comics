package com.example.gestash.comics.presentation.view

import com.arellomobile.mvp.MvpView
import com.example.gestash.comics.presentation.model.ComicsViewModel

interface PageView : MvpView {
    fun onComicsLoaded(data: ComicsViewModel)
    fun onComicsLoadFailure()
}