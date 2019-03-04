package com.androidui.draw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintAlignView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
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
        canvas?.drawLine(200f, 100f, 800f, 100f, paint)

        paint.color = Color.GREEN
        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(200f, 200f, 800f, 200f, paint)

        paint.color = Color.BLUE
        paint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(200f, 300f, 800f, 300f, paint)
    }

}