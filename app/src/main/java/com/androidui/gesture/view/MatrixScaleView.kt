package com.androidui.gesture.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.androidui.R

class MatrixScaleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint = Paint()
    val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girl)!!
    var mMatrix = Matrix()
    var sx = 1f
    var sy = 1f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mMatrix.setScale(sx, sy)
        canvas?.drawBitmap(bitmap, mMatrix, paint)
    }

}