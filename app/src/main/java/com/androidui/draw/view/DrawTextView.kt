package com.androidui.draw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class DrawTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint:Paint = Paint()

    init {
        setBackgroundColor(Color.WHITE)
        paint.textSize = 18f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val text = "这是用Paint绘制的文字"
        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        canvas?.drawText(text, 0,text.length,(width-rect.width())/2f, (height+rect.height()/2)/2f,paint)
    }

}