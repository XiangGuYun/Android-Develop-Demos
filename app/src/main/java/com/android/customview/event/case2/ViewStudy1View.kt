package com.android.customview.event.case2

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView

class ViewStudy1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        TextView(context, attrs, defStyleAttr){

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                text = "ACTION_DOWN\n(${event.x.toInt()}, ${event.y.toInt()})"
            }
            MotionEvent.ACTION_MOVE -> {
                text = "ACTION_MOVE\n(${event.x.toInt()}, ${event.y.toInt()})"
            }
            MotionEvent.ACTION_UP -> {
                text = "ACTION_UP"
            }
            MotionEvent.ACTION_CANCEL -> {
                text = "ACTION_CANCEL"
            }
            MotionEvent.ACTION_OUTSIDE -> {
                text = "ACTION_OUTSIDE"
            }
        }
        return super.onTouchEvent(event)
    }

}