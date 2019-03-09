package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils


class CanvasSkewView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()
    var sx: Float = 0f
    var sy: Float = 0f

    init {
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.CYAN)
        canvas?.skew(sx, sy)

        canvas?.drawLine(0f, height/2f, width.toFloat(), height/2f, paint)
        canvas?.drawLine(width/2f, 0f, width/2f, height.toFloat(), paint)

        canvas?.translate(width/2f,height/2f)

        canvas?.drawRect(-100f,-100f,100f,100f, paint)
    }



}