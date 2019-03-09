package com.android.draw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintCapView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    private val paint:Paint = Paint()

    init {
        setBackgroundColor(Color.WHITE)
        paint.strokeWidth = 50f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.BUTT
        canvas?.drawLine(50f, 50f, width.toFloat()-50, 50f, paint)

        paint.color = Color.GREEN
        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(50f, height.toFloat()/2, width.toFloat()-50, height.toFloat()/2, paint)

        paint.color = Color.BLUE
        paint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(50f, height.toFloat()-50, width.toFloat()-50, height.toFloat()-50f, paint)
    }

}