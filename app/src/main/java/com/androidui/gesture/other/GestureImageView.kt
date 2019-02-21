package com.androidui.gesture.other

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.androidui.R
import com.androidui.gesture.GestureListenerActivity
import com.androidui.gesture.GestureListenerActivity.MyGestureListener
import kotlinx.android.synthetic.main.header_view1.view.*

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