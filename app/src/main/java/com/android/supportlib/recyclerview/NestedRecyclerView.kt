package com.android.supportlib.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent

class NestedRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        RecyclerView(context, attrs, defStyleAttr){

    var xDistance: Float = 0f
    var yDistance: Float = 0f
    var xLast: Float = 0f
    var yLast: Float = 0f

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        when(e?.action){
            MotionEvent.ACTION_DOWN->{
                yDistance = 0f
                xDistance = 0f
                xLast = e.x
                yLast = e.y
            }
            MotionEvent.ACTION_MOVE->{
                val curX = e.x
                val curY = e.y

                xDistance += Math.abs(curX - xLast)
                yDistance += Math.abs(curY - yLast)
                xLast = curX
                yLast = curY

                if(xDistance > yDistance){
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(e)
    }

}