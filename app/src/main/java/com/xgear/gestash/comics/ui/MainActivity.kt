package com.xgear.gestash.comics.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDelegate
import android.view.View.GONE
import android.view.View.VISIBLE
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.xgear.gestash.comics.R
import com.xgear.gestash.comics.presentation.presenter.MainPresenter
import com.xgear.gestash.comics.presentation.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.util.*


class MainActivity : MvpAppCompatActivity(), MainView {

    private val PERMISSON_REQUEST_WRITE_EXTERNAL_STORAGE = 1

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mainPresenter: MainPresenter

    lateinit var pageAdapter: HorizontalPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO)//тема ночная
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        shareButton.onClick {
            val shareUrl = pageAdapter.getShareUrl() ?: return@onClick

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareUrl)
                type = "text/plain"
            }
            startActivity(sendIntent)
        }

        randomButton.onClick {
            val item = Random().nextInt(pageAdapter.count - 1) + 1
            viewPager.currentItem = item
        }

        downloadButton.onClick {

            saveImageWithPermission()
        }

        mainPresenter.loadComicsCount()
    }

    private fun saveImageWithPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSON_REQUEST_WRITE_EXTERNAL_STORAGE)
            } else {
                saveToGallery()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSON_REQUEST_WRITE_EXTERNAL_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                saveToGallery()
            }
        }
    }

    private fun saveToGallery() {
        if (pageAdapter.saveCurrentImage()) {
            toast(getString(R.string.savingToast))
        }
    }

    override fun onComicsCountLoaded(count: Int) {
        progressBar.visibility = GONE
        viewPager.visibility = VISIBLE

        pageAdapter = HorizontalPagerAdapter(supportFragmentManager, count)
        viewPager.adapter = pageAdapter
    }

    override fun onComicsCountLoadFailure() {
        progressBar.visibility = GONE
        errorView.visibility = VISIBLE
    }
}


