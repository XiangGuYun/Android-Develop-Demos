package com.android.gesture.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.android.R
import com.kotlinlib.activity.ContextUtils

class PTP1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girl1)
    val mMatrix = Matrix()
    var translateX = 0f
    var translateY = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val src = floatArrayOf(0f, 0f)
        val dst = floatArrayOf(translateX, translateY)
        mMatrix.setPolyToPoly(src, 0, dst, 0, 1)
        canvas?.drawBitmap(bitmap, mMatrix, paint)
    }

}