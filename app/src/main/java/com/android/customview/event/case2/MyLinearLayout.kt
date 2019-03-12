package com.android.customview.event.case2

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import com.kotlinlib.other.BaseInterface

class MyLinearLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr), BaseInterface{

    var tvParentLog:TextView?=null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                tvParentLog?.text = "ACTION_DOWN(${event.x.toInt()}, ${event.y.toInt()})"
            }
            MotionEvent.ACTION_MOVE -> {
                tvParentLog?.text = "ACTION_MOVE(${event.x.toInt()}, ${event.y.toInt()})"
            }
            MotionEvent.ACTION_UP -> {
                tvParentLog?.text =  "ACTION_UP"
            }
            MotionEvent.ACTION_CANCEL -> {
                tvParentLog?.text = "ACTION_CANCEL"
            }
            MotionEvent.ACTION_OUTSIDE -> {
                tvParentLog?.text = "ACTION_OUTSIDE"
            }
        }
        return true
    }

}