package com.android.customview.drag

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.android.R

class Drag1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    private var dragHelper: ViewDragHelper?=null
    private var paint = Paint()
    init {
        paint.color = Color.RED
        dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return false
//                child.tag == "DragMe1"
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                if(left<0){
                    return 0//禁止超过左边界
                }
                if(left>width/2-child.width){
                    return width/2-child.width//禁止超过右边界
                }
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                if(top<0){
                    return 0//禁止超过上边界
                }
                if(top>height/2-child.height){
                    return height/2-child.height//禁止超过下边界
                }
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
