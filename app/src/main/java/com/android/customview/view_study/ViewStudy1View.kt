package com.android.customview.view_study

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class ViewStudy1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    init {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

}