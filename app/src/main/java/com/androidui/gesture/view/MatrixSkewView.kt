package com.androidui.gesture.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.androidui.R

class MatrixSkewView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint = Paint()
    val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon)!!
    var mMatrix = Matrix()
    var kx = 0f
    var ky = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mMatrix.setSkew(kx, ky)
        canvas?.drawBitmap(bitmap, mMatrix, paint)
    }

}