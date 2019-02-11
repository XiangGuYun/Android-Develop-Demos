package com.androidui.huitu.view

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
        paint.strokeWidth = 30f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.strokeJoin = Paint.Join.BEVEL
        canvas?.drawRect(200f, 30f, 800f, 160f, paint)
        paint.color = Color.GREEN
        paint.strokeJoin = Paint.Join.MITER
        canvas?.drawRect(200f, 210f, 800f, 310f, paint)
        paint.color = Color.BLUE
        paint.strokeJoin = Paint.Join.ROUND
        canvas?.drawRect(200f, 360f, 800f, 510f, paint)
    }

}