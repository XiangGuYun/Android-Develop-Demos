package com.androidui.gesture.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.androidui.R

class MatrixRotateView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint = Paint()
    val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girl)!!
    var mMatrix = Matrix()
    var degree = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mMatrix.setRotate(degree, width/2f, height/2f)
        mMatrix.preTranslate(width/2f,height/2f)
        canvas?.drawBitmap(bitmap, mMatrix, paint)
    }

}