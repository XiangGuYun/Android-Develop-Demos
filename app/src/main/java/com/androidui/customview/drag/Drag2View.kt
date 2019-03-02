package com.androidui.customview.drag

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.androidui.R

class Drag2View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    private var dragHelper: ViewDragHelper?=null
    private var paint = Paint()
    init {
        paint.color = Color.RED
        dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return false
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

            override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
                dragHelper?.captureChildView(viewTest, pointerId)
            }

        })

        dragHelper?.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT)
    }

    lateinit var viewTest:View

    override fun onFinishInflate() {
        super.onFinishInflate()
        viewTest = findViewById(R.id.tvDragMe)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper?.shouldInterceptTouchEvent(ev!!)!!
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper?.processTouchEvent(event!!)
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}
