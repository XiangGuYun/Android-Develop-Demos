package com.androidui.gesture.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.androidui.R
import com.kotlinlib.activity.ContextUtils

class RotateImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    private var listener = DragGestureListener()
    private var detector: GestureDetector
    var paint = Paint()
    private var bmp: Bitmap
    var mMatrix = Matrix()
    var cX = 0f
    var cY = 0f
    var rotate = 0f

    init {
        setBackgroundColor(Color.GRAY)
        bmp = BitmapFactory.decodeResource(resources, R.mipmap.girl1)
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
        canvas?.drawBitmap(bmp, mMatrix, null)
    }

    inner class DragGestureListener: GestureDetector.SimpleOnGestureListener(){

        override fun onDown(ev: MotionEvent?): Boolean {
            if(ev!=null){
                cX = ev.x
                cY = ev.y
                mMatrix.setTranslate(ev.x, ev.y)
                invalidate()
            }
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
//            cX -= distanceX
//            cY -= distanceY
//            mMatrix.setTranslate(cX, cY)
            Log.d("asdasd", "${e1?.x} ${e1?.y} ${e2?.x} ${e2?.y}")
            rotate+=1f
            mMatrix.setRotate(rotate)
            invalidate()
            return false
        }

    }

}