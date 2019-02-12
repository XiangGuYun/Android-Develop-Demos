package com.androidui.huitu.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class DrawPointView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint:Paint = Paint()
    var canvas:Canvas? = null
    var cx = -1f
    var cy = -1f

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas
        if(cx == -1f && cy == -1f){
            cx = width/2f
            cy = height/2f
        }
        canvas?.drawPoint(cx, cy, paint)
    }

}