package com.androidui.supportlib.recyclerview

import android.content.Context
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.kotlinlib.other.DensityUtils

class SlideLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    private var dragHelper: ViewDragHelper?=null
    var currLeft = 0

    init {
        dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return child.tag == "drag"
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                currLeft = left
                val childWidth = DensityUtils.dip2px(context, -60f)
                if(left< childWidth){
                    return childWidth//禁止超过左边界
                }
                if(left>width-child.width-childWidth){
                    return width-child.width-childWidth//禁止超过右边界
                }
                return left
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                if (releasedChild.tag == "drag"&&currLeft>0)
                {
                    dragHelper?.settleCapturedViewAt(0, 0)
                    invalidate()
                }
            }

        })
    }

    override fun computeScroll() {
        if (dragHelper?.continueSettling(true)!!)
        {
            invalidate()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper?.shouldInterceptTouchEvent(ev!!)!!
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper?.processTouchEvent(event!!)
        return true
    }



}