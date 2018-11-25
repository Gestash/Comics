package com.xgear.gestash.comics.presentation.view

import com.arellomobile.mvp.MvpView
import com.xgear.gestash.comics.presentation.model.ComicsViewModel
import java.io.File

interface PageView : MvpView {
    fun onComicsLoaded(data: ComicsViewModel)
    fun onComicsLoadFailure()

    fun onImageLoaded(file: File)
    fun onImageLoadFailure()

}