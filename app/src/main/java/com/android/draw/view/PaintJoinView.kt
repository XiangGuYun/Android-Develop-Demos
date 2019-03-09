package com.android.draw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintJoinView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    private val paint:Paint = Paint()

    init {
        setBackgroundColor(Color.WHITE)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.strokeJoin = Paint.Join.BEVEL
        canvas?.drawRect(50f, 20f, width-50f, height.toFloat()/3-20, paint)
        paint.color = Color.GREEN
        paint.strokeJoin = Paint.Join.MITER
        canvas?.drawRect(50f, height.toFloat()/3, width-50f, height.toFloat()/3*2-20, paint)
        paint.color = Color.BLUE
        paint.strokeJoin = Paint.Join.ROUND
        canvas?.drawRect(50f, height.toFloat()/3*2, width-50f, height.toFloat()-20, paint)
    }

}