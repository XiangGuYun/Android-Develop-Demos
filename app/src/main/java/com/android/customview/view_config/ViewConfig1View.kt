package com.android.customview.view_config

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewConfiguration


class ViewConfig1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    init {
        val config = ViewConfiguration.get(context)
        //  获取touchSlop （系统 滑动距离的最小值，大于该值可以认为滑动）
        val touchSlop = config.scaledTouchSlop
        //  获得允许执行fling （抛）的最小速度值
        val minimumVelocity = config.scaledMinimumFlingVelocity
        //  获得允许执行fling （抛）的最大速度值
        val maximumVelocity = config.scaledMaximumFlingVelocity
        //  Report if the device has a permanent menu key available to the user
        //  （报告设备是否有用户可找到的永久的菜单按键）
        //  即判断设备是否有返回、主页、菜单键等实体按键（非虚拟按键）
        val hasPermanentMenuKey = config.hasPermanentMenuKey()
    }

}