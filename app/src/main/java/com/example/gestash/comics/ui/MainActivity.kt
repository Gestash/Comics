package com.example.gestash.comics.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.view.View.GONE
import android.view.View.VISIBLE
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.gestash.comics.R
import com.example.gestash.comics.presentation.presenter.MainPresenter
import com.example.gestash.comics.presentation.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mainPresenter: MainPresenter

    lateinit var pageAdapter: HorizontalPagerAdapter

    var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO)//тема ночная
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        shareButton.onClick {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            }
            startActivity(sendIntent)
        }

        randomButton.onClick {
            val item = Random().nextInt(pageAdapter.count - 1) + 1
            viewPager.currentItem = item
        }

        mainPresenter.loadComicsCount()
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


