package com.android.customview.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class NestedLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ScrollView(context, attrs, defStyleAttr){

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return false
    }


}
