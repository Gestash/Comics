package com.example.gestash.comics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.gestash.comics.R
import com.example.gestash.comics.presentation.model.ComicsViewModel
import com.example.gestash.comics.presentation.presenter.PageViewPresenter
import com.example.gestash.comics.presentation.view.PageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.page_view_fragment.*


class PageViewFragment : MvpAppCompatFragment(), PageView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: PageViewPresenter

    var comicsNum = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate( R.layout.page_view_fragment, container, false)

        presenter.getComicsByNumber(comicsNum)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainTitle.text = ""
        comicsDate.text = ""
        comicsNumber.text = ""
    }

    override fun onComicsLoaded(data: ComicsViewModel) {
        mainTitle.text = data.title
        comicsDate.text = data.date
        comicsNumber.text = data.number

        Picasso.get().load(data.imageUrl).into(imageView)
    }

    override fun onComicsLoadFailure() {
        //TODO
    }
}