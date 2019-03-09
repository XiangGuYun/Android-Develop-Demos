package com.android.gesture.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.android.R
import com.kotlinlib.activity.ContextUtils

class PTP2View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girl1)
    val mMatrix = Matrix()
    var changeX = 0f
    var changeY = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val bw = bitmap.width.toFloat()
        val bh = bitmap.height.toFloat()
        val src = floatArrayOf(bw/2, bh/2, //旋转中心保持不变
                bw, 0f)
        val dst = floatArrayOf(bw/2, bh/2, //旋转中心保持不变
                bw / 2 + bh / 2, bh / 2 + bw)
        mMatrix.setPolyToPoly(src, 0, dst, 0, 2)
        canvas?.drawBitmap(bitmap, mMatrix, paint)
    }

}