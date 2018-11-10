package com.xgear.gestash.comics.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.xgear.gestash.comics.R
import com.xgear.gestash.comics.presentation.model.ComicsViewModel
import com.xgear.gestash.comics.presentation.presenter.PageViewPresenter
import com.xgear.gestash.comics.presentation.view.PageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.page_view_fragment.*
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import java.io.File
import java.io.FileOutputStream


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

    override fun onDownloadComics(){
        imageView.invalidate()
        val image_name = "name"
        val drawable = imageView.drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File(root)
        myDir.mkdirs()
        val fname = "Image-$image_name.jpg"
        val file = File(myDir, fname)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}