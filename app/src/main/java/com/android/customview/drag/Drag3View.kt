package com.android.customview.drag

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.android.R



class Drag3View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    private var dragHelper: ViewDragHelper?=null
    private var paint = Paint()
    init {
        paint.color = Color.RED
        dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                if (releasedChild == viewTest)
                {
                    dragHelper?.smoothSlideViewTo(viewTest,width-viewTest.width,height-viewTest.height)
                    //
                    invalidate()
                }
            }

        })

    }

    lateinit var viewTest:View
    lateinit var viewTest1:View

    override fun computeScroll() {
        if (dragHelper?.continueSettling(true)!!)
        {
            invalidate()
        }
    }

    var autoBackViewOriginLeft: Int = 0
    var autoBackViewOriginTop: Int = 0

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        autoBackViewOriginLeft = viewTest.left
        autoBackViewOriginTop = viewTest.top
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        viewTest = findViewById(R.id.tvDragMe)
        viewTest1 = findViewById(R.id.tvDragMe1)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper?.shouldInterceptTouchEvent(ev!!)!!
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper?.processTouchEvent(event!!)
        return true
    }


}
