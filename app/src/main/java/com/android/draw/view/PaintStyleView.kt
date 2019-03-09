package com.android.draw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintStyleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    private val paint:Paint = Paint()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(100f,height/2f,40f, paint)
        paint.reset()
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 15f
        canvas?.drawCircle(width/2f,height/2f,40f, paint)
        paint.reset()
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 15f
        canvas?.drawCircle(width.toFloat()-100,height/2f,40f, paint)
    }

}