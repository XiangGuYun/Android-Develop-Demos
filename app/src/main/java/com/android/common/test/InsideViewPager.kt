package com.android.common.test

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import com.kotlinlib.other.DensityUtils

class InsideViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    var canScrollToNext = true//当ViewPager滑到最后一页时，该值为false
    var lastX = 0
    var lastY = 0
    var isInter = false//是否拦截

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val x = ev?.x?.toInt()!!
        val y = ev?.y?.toInt()!!
        when(ev.action){
            MotionEvent.ACTION_DOWN->isInter = super.dispatchTouchEvent(ev)
            MotionEvent.ACTION_UP->isInter = super.dispatchTouchEvent(ev)
            MotionEvent.ACTION_MOVE->{
                if(DensityUtils.dip2px(context,200f) >= y//确定当前触摸的位置在ViewPager上
                        &&!canScrollToNext//确定ViewPager已经滑到最后一页
                        &&x<lastX//确定用户的操作是向右滑
                )
                    isInter = true//拦截
                else
                    isInter = super.dispatchTouchEvent(ev)
            }
            else->isInter = super.dispatchTouchEvent(ev)
        }
        lastX = x
        lastY = y
        return isInter
    }

}