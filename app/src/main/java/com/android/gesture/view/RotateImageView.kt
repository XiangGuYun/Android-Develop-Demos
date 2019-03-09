package com.android.gesture.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.android.R
import com.kotlinlib.activity.ContextUtils

class RotateImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    private var listener = DragGestureListener()
    private var detector: GestureDetector
    var paint = Paint()
    private var bmp: Bitmap
    var mMatrix = Matrix()
    var rotate = 0f

    init {
        setBackgroundColor(Color.BLACK)
        bmp = BitmapFactory.decodeResource(resources, R.mipmap.img3)
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
        canvas?.translate(width/2f-bmp.width/2,height/2f-bmp.height/2)
        canvas?.scale(width/bmp.width.toFloat(),width/bmp.width.toFloat(), bmp.width/2f,bmp.height/2f)
        canvas?.drawBitmap(bmp, mMatrix, null)
    }

    inner class DragGestureListener: GestureDetector.SimpleOnGestureListener(){

        override fun onDown(ev: MotionEvent?): Boolean {
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if(e1!=null&&e2!=null){
                val a = Math.sqrt(Math.pow(Math.abs(bmp.width/2f-e1.x).toDouble(),2.0)+Math.pow(Math.abs(bmp.height/2-e1.y).toDouble(),2.0))
                val b = Math.sqrt(Math.pow(Math.abs(bmp.width/2-e2.x).toDouble(),2.0)+Math.pow(Math.abs(bmp.height/2-e2.y).toDouble(),2.0))
                val c = Math.sqrt(Math.pow(Math.abs(e2.x-e1.x).toDouble(),2.0)+Math.pow(Math.abs(e2.y-e1.y).toDouble(),2.0))
                val cosA = (c*c+b*b-a*a)/(2*b*c)
                val cosB = (a*a+c*c-b*b)/(2*a*c)
                val cosC = (a*a+b*b-c*c)/(2*a*b)
                val all = Math.acos(cosA)+Math.acos(cosB)+Math.acos(cosC)
                rotate+=(Math.acos(cosC)/all*180).toFloat()/20
                mMatrix.setRotate(rotate, bmp.width/2f, bmp.height/2f)
                invalidate()
            }
            return false
        }

    }

}