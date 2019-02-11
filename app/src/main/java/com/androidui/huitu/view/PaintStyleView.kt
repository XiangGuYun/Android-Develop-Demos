package com.androidui.huitu.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintStyleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    private val paint:Paint = Paint()

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawRect(0f, 0f, width.toFloat(), 150f, paint)
        paint.reset()
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 15f
        canvas?.drawRect(0f, 150f, width.toFloat(), 300f, paint)
        paint.reset()
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas?.drawRect(0f, 300f, width.toFloat(), 450f, paint)
    }

}