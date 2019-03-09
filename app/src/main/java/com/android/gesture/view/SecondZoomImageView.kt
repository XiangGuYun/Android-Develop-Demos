package com.android.gesture.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.android.R

class SecondZoomImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val mMatrix = Matrix()
    var currentScale = 1f//当前的缩放率
    var maxScale = 5f//最大的缩放率
    var minScale = 0.1f//最小的缩放率
    val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img3)

    init {
        setBackgroundColor(Color.BLACK)
    }

    //缩放监听
    var listener = ScaleGestureDetector(context,
            object :ScaleGestureDetector.SimpleOnScaleGestureListener(){
                override fun onScale(detector: ScaleGestureDetector?): Boolean {
                    if(detector!=null&&currentScale*detector.scaleFactor>=minScale&&currentScale*detector.scaleFactor<=maxScale){
                        mMatrix.preTranslate(width/2f,height/2f)
                        mMatrix.setScale(currentScale*detector.scaleFactor, currentScale*detector.scaleFactor, bitmap.width/2f, bitmap.height/2f)
                        invalidate()
                        currentScale *= detector.scaleFactor
                    }
                    return true
                }
            })

    //双击监听
    val detector =GestureDetector(object :GestureDetector.SimpleOnGestureListener(){
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            currentScale = 1f
            mMatrix.setScale(1f, 1f, bitmap.width/2f, bitmap.height/2f)
            invalidate()
            return false
        }


    })

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(width/2f-bitmap.width/2,height/2f-bitmap.height/2)
        canvas?.scale(width/bitmap.width.toFloat(),width/bitmap.width.toFloat(), bitmap.width/2f,bitmap.height/2f)
        canvas?.drawBitmap(bitmap, mMatrix, null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        listener.onTouchEvent(event)
        detector.onTouchEvent(event)
        return true
    }

}

//        override fun onDown(ev: MotionEvent?): Boolean {
//            if(ev!=null){
//                cX = ev.x-width/2
//                cY = ev.y-height/2
//            }
//            return false
//        }
//
//        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
//            if(!listener.isInProgress){
//                cX -= distanceX
//                cY -= distanceY
//                mMatrix.setTranslate(cX, cY)
//                invalidate()
//            }
//            return false
//        }