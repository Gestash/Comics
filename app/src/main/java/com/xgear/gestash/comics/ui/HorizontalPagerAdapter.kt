package com.xgear.gestash.comics.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup


class HorizontalPagerAdapter(fm: FragmentManager, private val pagesCount: Int) : FragmentStatePagerAdapter(fm) {

    private var currentComicsFragment: PageViewFragment? = null

    override fun getItem(position: Int): Fragment {
        val comicsNumber = pagesCount - position
        val fragment = PageViewFragment()
        fragment.comicsNum = comicsNumber
        return fragment
    }

    override fun getCount(): Int {
        return pagesCount - 1
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        currentComicsFragment = `object` as? PageViewFragment
    }

    fun getShareUrl(): String? {
        return currentComicsFragment?.presenter?.comics?.imageUrl
    }

    fun saveCurrentImage(): Boolean {
        return currentComicsFragment?.presenter?.saveImageToGallery() ?: return false
    }
}

