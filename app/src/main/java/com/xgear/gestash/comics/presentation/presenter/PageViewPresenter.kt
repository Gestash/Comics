package com.xgear.gestash.comics.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.xgear.gestash.comics.ComicsApplication
import com.xgear.gestash.comics.presentation.model.ComicsViewModel
import com.xgear.gestash.comics.presentation.view.PageView
import java.io.File


@InjectViewState
class PageViewPresenter : MvpPresenter<PageView>() {

    private val comicsProvider = ComicsApplication.getComponent().comicsProvider()
    private val imageProvider = ComicsApplication.getComponent().imageProvider()
    private val gallerySaver = ComicsApplication.getComponent().gallerySaver()
    private var currentImageFile: File? = null

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

    fun getImageFile(imageUrl: String) {
        imageProvider.getImageFile(imageUrl) {
            if (it != null) {
                viewState.onImageLoaded(it)
                currentImageFile = it
            } else {
                viewState.onImageLoadFailure()
            }
        }
    }

    fun saveImageToGallery():Boolean {
        val file = currentImageFile ?: return false
        gallerySaver.saveToGallery(file)
        return true
    }
}