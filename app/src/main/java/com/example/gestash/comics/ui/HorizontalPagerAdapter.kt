package com.example.gestash.comics.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class HorizontalPagerAdapter(fm: FragmentManager, private val pagesCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val comicsNumber = pagesCount - position
        val fragment = PageViewFragment()
        fragment.comicsNum = comicsNumber
        return fragment
    }

    override fun getCount(): Int {
        return pagesCount - 1
    }
}

