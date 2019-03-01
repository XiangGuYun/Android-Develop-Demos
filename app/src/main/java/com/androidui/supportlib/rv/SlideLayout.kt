package com.androidui.supportlib.rv

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.FrameLayout

class SlideLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    private var listener = DragGestureListener()
    private var detector: GestureDetector
    var maxMoveValue = 200f
    var moveValue = 0f

    init {
        detector = GestureDetector(context, listener)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        detector.onTouchEvent(ev)
        return true
    }


    inner class DragGestureListener: GestureDetector.SimpleOnGestureListener(){

        override fun onDown(ev: MotionEvent?): Boolean {
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            moveValue += Math.abs(e2!!.x-e1!!.x)
            if(moveValue<=maxMoveValue){
                if(moveValue>=0){
                    getChildAt(1).translationX = -moveValue
                }
            }
            return false
        }

    }


}