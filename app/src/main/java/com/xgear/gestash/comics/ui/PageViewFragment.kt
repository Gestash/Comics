package com.xgear.gestash.comics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.squareup.picasso.Picasso
import com.xgear.gestash.comics.R
import com.xgear.gestash.comics.presentation.model.ComicsViewModel
import com.xgear.gestash.comics.presentation.presenter.PageViewPresenter
import com.xgear.gestash.comics.presentation.view.PageView
import kotlinx.android.synthetic.main.page_view_fragment.*
import org.jetbrains.anko.support.v4.longToast
import java.io.File


class PageViewFragment : MvpAppCompatFragment(), PageView {


    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: PageViewPresenter

    var comicsNum = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.page_view_fragment, container, false)

        presenter.getComicsByNumber(comicsNum)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainTitle.text = ""
        comicsDate.text = ""
        comicsNumber.text = ""
    }

    override fun onComicsLoaded(data: ComicsViewModel) {
        progressBarForFragment.visibility = GONE
        mainTitle.text = data.title
        comicsDate.text = data.date
        comicsNumber.text = data.number

        presenter.getImageFile(data.imageUrl)

    }

    override fun onComicsLoadFailure() {
        longToast(getString(R.string.errorView))
    }

    override fun onImageLoaded(file: File) {
        Picasso.get().load(file).into(imageView)
    }

    override fun onImageLoadFailure() {
        //todo button try again
    }
}