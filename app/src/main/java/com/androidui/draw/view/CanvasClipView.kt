package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils


class CanvasClipView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()

    var regionOption = Region.Op.DIFFERENCE

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.CYAN)
        paint.color = Color.RED
        canvas?.drawRect(0f,0f,width/2f,height/2f,paint)
        paint.color = Color.BLUE
        canvas?.drawRect(width/2f+30,30f,width.toFloat()-30,height/2f-30,paint)
        canvas?.drawLine(0f, height/2f, width.toFloat(), height/2f, paint)
        canvas?.drawLine(width/2f, 0f, width/2f, height.toFloat(), paint)
        canvas?.translate(width/2f,height/2f)

        //首次裁剪
        canvas?.clipRect(0f,0f,width/2f,height/2f)
        canvas?.drawColor(Color.RED)
        //二次裁剪
        canvas?.clipRect(30f,30f,width/2f-30,height/2f-30, regionOption)
        canvas?.drawColor(Color.BLUE)

    }



}