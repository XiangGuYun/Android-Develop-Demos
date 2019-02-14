package com.androidui.supportlib.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.androidui.R

class Vp1Adapter(var datas: List<ImageView>) : PagerAdapter() {

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = datas[position]

        view.setImageResource(when(position){
            0-> R.mipmap.header1
            1-> R.mipmap.header2
            else-> R.mipmap.header3
        })
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(datas[position])
    }

}