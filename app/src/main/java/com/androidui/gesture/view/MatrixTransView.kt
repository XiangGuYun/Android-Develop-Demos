package com.androidui.gesture.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.androidui.R

class MatrixTransView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint = Paint()
    val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girl)!!
    var mMatrix = Matrix()
    var dx = 0f
    var dy = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mMatrix.setTranslate(dx, dy)
        canvas?.drawBitmap(bitmap, mMatrix, paint)
    }

}