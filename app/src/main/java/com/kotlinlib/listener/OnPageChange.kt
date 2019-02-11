package com.kotlinlib.listener

import android.support.v4.view.ViewPager

interface OnPageChange: ViewPager.OnPageChangeListener{

    fun ViewPager.listenPageChange(event: OnPageChange){
        setOnPageChangeListener(event)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }
}