package com.android.gesture.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.kotlinlib.activity.ContextUtils

class DragImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    private var listener = DragGestureListener()
    private var detector: GestureDetector
    var circleX = 100f
    var circleY = 100f
    var paint = Paint()

    init {
        setBackgroundColor(Color.CYAN)
        paint.color = Color.RED
        detector = GestureDetector(context, listener)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        detector.onTouchEvent(ev)
        // 一定要返回true，不然获取不到完整的事件
        // 如果是Activity则可以直接返回detector.onTouchEvent(ev)
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(circleX, circleY, 50f, paint)
    }

    inner class DragGestureListener: GestureDetector.SimpleOnGestureListener(){

        override fun onDown(ev: MotionEvent?): Boolean {
            if(ev!=null){
                circleX = ev.x
                circleY = ev.y
                invalidate()
            }
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            circleX -= distanceX
            circleY -= distanceY
            invalidate()
            return false
        }

    }

}