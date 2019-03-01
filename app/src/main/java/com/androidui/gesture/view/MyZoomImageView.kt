package com.androidui.gesture.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.androidui.R

class MyZoomImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ImageView(context, attrs, defStyleAttr){

    val mMatrix = Matrix()
    var currentScale = 1f//当前的缩放率
    var maxScale = 5f//最大的缩放率
    var minScale = 0.1f//最小的缩放率

    init {
        scaleType = ScaleType.MATRIX
    }

    var listener:ScaleGestureDetector = ScaleGestureDetector(context,
            object :ScaleGestureDetector.OnScaleGestureListener{

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {

            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {

        }

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            if(detector!=null&&currentScale*detector.scaleFactor>=minScale&&currentScale*detector.scaleFactor<=maxScale){
                mMatrix.preTranslate(width/2f,height/2f)
                mMatrix.setScale(currentScale*detector.scaleFactor, currentScale*detector.scaleFactor)
                imageMatrix = mMatrix
                currentScale *= detector.scaleFactor
            }
            return true
        }
    })


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return listener.onTouchEvent(event)
    }

}