package com.android.gesture.other

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import com.android.gesture.GestureListenerActivity.MyGestureListener

class GestureImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ImageView(context, attrs, defStyleAttr){

    private var listener: MyGestureListener = MyGestureListener()
    private var detector: GestureDetector

    init {
        detector = GestureDetector(context, listener)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        detector.onTouchEvent(ev)
        // 一定要返回true，不然获取不到完整的事件
        // 如果是Activity则可以直接返回detector.onTouchEvent(ev)
        return true
    }


}