package com.kotlinlib.listener

import android.support.design.widget.TabLayout

interface OnTabSelected:TabLayout.OnTabSelectedListener {

    fun TabLayout.listenTabSelected(event:TabLayout.OnTabSelectedListener){
        setOnTabSelectedListener(event)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

}