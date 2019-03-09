package com.android.customview.drag

import android.content.Context
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class DragView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    var dragHelper: ViewDragHelper?=null
    var tv1:TextView?=null//记录参数
    var tv2:TextView?=null//记录参数
    var tv3:TextView?=null//记录参数

    init {
        dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                tv1?.text = "child's tag is ${child.tag}, pointerId is $pointerId"
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                tv2?.text = "child's tag is ${child.tag}, left is $left, dx is $dx"
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                tv3?.text = "child's tag is ${child.tag}, top is $top, dy is $dy"
                return top
            }

        })

    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper?.shouldInterceptTouchEvent(ev!!)!!
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper?.processTouchEvent(event!!)
        return true
    }

}
